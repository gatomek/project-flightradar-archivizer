package pl.gatomek.flightradar.eventsaver.adapter.in.rabbit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AircraftLog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private Instant timestamp;

    @JsonProperty("hex")
    private String icao;

    @JsonProperty("flight")
    private String flight;

    @JsonProperty("lat")
    private BigDecimal latitude;

    @JsonProperty("lon")
    private BigDecimal longitude;

    @JsonProperty("alt_baro")
    private String barometricAltitude;
}
