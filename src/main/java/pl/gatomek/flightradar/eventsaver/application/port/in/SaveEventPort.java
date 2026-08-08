package pl.gatomek.flightradar.eventsaver.application.port.in;

import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

import java.util.List;

public interface SaveEventPort {
    void saveEvents(List<Event> events);
}
