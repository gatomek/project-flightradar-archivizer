package pl.gatomek.flightradar.eventsaver.application.port.in;

import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

import java.util.List;

public interface SaveEventUseCase {
    void saveEvents(List<Event> events);
}
