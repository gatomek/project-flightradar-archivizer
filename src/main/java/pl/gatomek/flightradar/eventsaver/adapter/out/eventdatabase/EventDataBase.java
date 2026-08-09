package pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.mapper.ToEntityMapper;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.model.EventEntity;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.repo.EventRepository;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;
import pl.gatomek.flightradar.eventsaver.application.port.out.PersistEventPort;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventDataBase implements PersistEventPort {

    private final EventRepository aircraftEventRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistSingleEvent(Event event) {
        EventEntity found = aircraftEventRepository.findEventByIcaoAndTimestamp(event.getIcao(), event.getTimestamp());
        if( found != null) {
            return;
        }

        EventEntity eventEntity = ToEntityMapper.INSTANCE.toEntity(event);
        aircraftEventRepository.saveAndFlush(eventEntity);
    }
}
