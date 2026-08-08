package pl.gatomek.flightradar.eventsaver.application.port.out;

import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

import java.util.List;

public interface PersistEventPort {
    void persistEvents(List<Event> events);
}
