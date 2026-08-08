package pl.gatomek.flightradar.eventsaver.application.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;
import pl.gatomek.flightradar.eventsaver.application.port.in.SaveEventPort;
import pl.gatomek.flightradar.eventsaver.application.port.out.PersistEventPort;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SaveEventService implements SaveEventPort {
    private final PersistEventPort persistEventPort;

    @Override
    public void saveEvents(List<Event> events) {
        persistEventPort.persistEvents(events);
    }
}
