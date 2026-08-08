package pl.gatomek.flightradar.eventsaver.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    private Instant timestamp;
    private String icao;
    private String flight;
    private String registerNumber;
    private String type;
    private String desc;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String barometricAltitude;
    private String geometricAltitude;
    private BigDecimal mach;
    private String emitterCategory;
    private String emergency;
    private Integer dbFlags;
    private Integer messages;
    private BigDecimal magneticHeading;
    private BigDecimal trueHeading;
    private List<String> navModes;
    private String squawk;
    private String groundSpeed;
}
