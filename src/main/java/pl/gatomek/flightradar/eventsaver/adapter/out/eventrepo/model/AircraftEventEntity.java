package pl.gatomek.flightradar.eventsaver.adapter.out.eventrepo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "event", schema = "radar_eventsaver")
public class AircraftEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name = "event_seq", sequenceName = "event_id_seq", allocationSize = 1, schema = "radar_eventsaver")
    private Long id;

    private Instant timestamp;

    @Column(name = "icao", length = 20, nullable = false)
    private String icao;

    @Column(name = "flight", length = 20)
    private String flight;

    @Column(name = "latitude", precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(name = "description")
    private String desc;

    @Column(name = "alt_baro")
    private String barometricAltitude;

    @Column(name = "type")
    private String type;
}
