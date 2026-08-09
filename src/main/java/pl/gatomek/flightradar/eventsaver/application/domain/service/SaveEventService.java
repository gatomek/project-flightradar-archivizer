package pl.gatomek.flightradar.eventsaver.application.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;
import pl.gatomek.flightradar.eventsaver.application.port.in.SaveEventPort;
import pl.gatomek.flightradar.eventsaver.application.port.out.PersistEventPort;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class SaveEventService implements SaveEventPort {
    private final PersistEventPort persistEventPort;

    @Override
    public void saveEvents(List<Event> events) {
        for (Event event : events) {
            try {
                persistEventPort.persistSingleEvent(event);
            } catch (DataIntegrityViolationException ex) {
                log.warn("Data Integrity Violation", ex);
            }
        }
    }
}
