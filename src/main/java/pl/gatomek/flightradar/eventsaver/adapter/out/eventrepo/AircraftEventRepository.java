package pl.gatomek.flightradar.eventsaver.adapter.out.eventrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventrepo.model.AircraftEventEntity;

import java.util.List;

@Repository
public interface AircraftEventRepository extends JpaRepository<AircraftEventEntity, Long> {

    // @formatter:off
    @Query( """
        select al from AircraftEventEntity al
        where al.id < :logId and al.icao = :icao
        order by al.id desc limit 1
        """
    )
    // @formatter:on
    AircraftEventEntity findPrevLog(@Param("logId") Long logId, @Param("icao") String icao);

    // @formatter:off
    @Query( """
        select al from AircraftEventEntity al
        where al.icao = :icao
        order by al.id asc
        """
    )
    // @formatter:on
    List<AircraftEventEntity> getAllLogsForIcao(@Param("icao") String icao);

    // @formatter:off
    @Query( """
        select al from AircraftEventEntity al
        where al.icao = :icao and al.id > :logId
        order by al.id asc
        """
    )
    // @formatter:on
    List<AircraftEventEntity> getAllLogsForIcaoAfterLogId(@Param("icao") String icao, @Param("logId") Long logId);

    // @formatter:off
    @Query( """
        select al from AircraftEventEntity al
        where al.icao = :icao and al.id >= :beginLogId
        order by al.id asc
        """
    )
    // @formatter:on
    List<AircraftEventEntity> getLogsForIcao(@Param("icao") String icao, @Param("beginLogId") Long beginLogId);

    // @formatter:off
    @Query( """
        select al from AircraftEventEntity al
        where al.icao = :icao and al.id >= :beginLogId and al.id <= :endLogId
        order by al.id asc
        """
    )
    // @formatter:on
    List<AircraftEventEntity> getLogsForIcao(@Param("icao") String icao, @Param("beginLogId") Long beginLogId, @Param("endLogId") Long endLogId);
}
