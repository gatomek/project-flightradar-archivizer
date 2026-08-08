package pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.mapper.ToEntityMapper;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.model.EventEntity;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;
import pl.gatomek.flightradar.eventsaver.application.port.out.PersistEventPort;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventDataBase implements PersistEventPort {

    private final pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.repo.EventRepository aircraftEventRepository;

    @Override
    public void persistEvents(List<Event> events) {
        List<EventEntity> entities = ToEntityMapper.INSTANCE.toEntities(events);
        for( EventEntity e : entities) {
            try {
                aircraftEventRepository.save(e);
            }
            catch (Exception ex) {
                log.error("persistEvent: {}", e.getIcao(), ex);
            }
        }
    }
}
