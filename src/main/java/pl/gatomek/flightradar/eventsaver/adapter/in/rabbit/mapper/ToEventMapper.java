package pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.model.AircraftLog;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

import java.time.Instant;

@Mapper
public interface ToEventMapper {
    ToEventMapper INSTANCE = Mappers.getMapper(ToEventMapper.class);

    Event toEvent(AircraftLog log, @Context Instant timestamp);

    @AfterMapping
    default void applyTimestamp(@MappingTarget Event event, @Context Instant timestamp) {
        event.setTimestamp(timestamp);
    }
}
