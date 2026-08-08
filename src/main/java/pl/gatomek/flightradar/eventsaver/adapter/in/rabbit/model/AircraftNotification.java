package pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AircraftNotification {

    @JsonProperty("ac")
    private List<AircraftLog> aircraftLogs;

    @JsonProperty("msg")
    private String status;

    @JsonProperty("now")
    private Long timestamp;

    private Integer total;

    private Long ctime;

    private Long ptime;
}
