package pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.model.AircraftLog;
import pl.gatomek.flightradar.eventsaver.application.domain.model.Event;

@Mapper
public interface ToEventMapper {
    ToEventMapper INSTANCE = Mappers.getMapper(ToEventMapper.class);

    Event toEvent(AircraftLog log);
}
