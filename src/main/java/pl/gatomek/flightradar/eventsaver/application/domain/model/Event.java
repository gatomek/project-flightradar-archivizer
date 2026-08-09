package pl.gatomek.flightradar.eventsaver.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    private Instant timestamp;
    private String icao;
    private String flight;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String barometricAltitude;
}
