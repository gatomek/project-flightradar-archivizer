package pl.gatomek.flightradar.eventsaver.application.port.out;

import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

public interface PersistEventUseCase {
    void persistSingleEvent(Event event);
}
