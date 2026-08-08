package pl.gatomek.flightradar.eventsaver.adapter.in.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.mapper.ToEventMapper;
import pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.model.AircraftLog;
import pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.model.AircraftNotification;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;
import pl.gatomek.flightradar.eventsaver.application.port.in.SaveEventPort;
import tools.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

@RequiredArgsConstructor
@Slf4j
@Component
public class EventNotificationListener {
    private static final String GZIP = "gzip";
    private static final String RADAR_EVENT = "RADAR_EVENT";
    private final SaveEventPort saveEventPort;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RADAR_EVENT, concurrency = "4")
    public void receiveMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();

        try {
            String contentEncoding = messageProperties.getContentEncoding();
            if (GZIP.equals(contentEncoding)) {
                AircraftNotification an = fromGZip(message.getBody());

                Long ctime = an.getCtime();
                Instant timestamp = ctime != null ? Instant.ofEpochMilli(ctime) : Instant.now();

                List<Event> events = new ArrayList<>(an.getAircraftLogs().size());

                for (AircraftLog log : an.getAircraftLogs()) {
                    Event e = ToEventMapper.INSTANCE.toEvent(log);
                    e.setTimestamp(timestamp);
                    events.add(e);
                }

                saveEventPort.saveEvents(events);
                return;
            }

            throw new UnsupportedOperationException("Content encoding not supported: "
                    + (contentEncoding != null ? contentEncoding : "none"));
        } catch (Exception e) {
            log.error("Receive message error", e);
        }
    }

    private AircraftNotification fromGZip(byte[] bytes) throws IOException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
             InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8)) {
            return objectMapper.readValue(inputStreamReader, AircraftNotification.class);
        }
    }
}
