package pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.model.EventEntity;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

import java.util.List;

@Mapper
public interface ToEntityMapper {

    ToEntityMapper INSTANCE = Mappers.getMapper(ToEntityMapper.class);

    EventEntity toEntity(Event event);

    List<EventEntity> toEntities(List<Event> events);
}
