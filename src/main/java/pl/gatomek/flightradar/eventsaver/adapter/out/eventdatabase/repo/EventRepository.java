package pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.gatomek.flightradar.eventsaver.adapter.out.eventdatabase.model.EventEntity;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    // @formatter:off
    @Query( """
        select al from EventEntity al
        where al.id < :logId and al.icao = :icao
        order by al.id desc limit 1
        """
    )
    // @formatter:on
    EventEntity findPrevLog(@Param("logId") Long logId, @Param("icao") String icao);

    // @formatter:off
    @Query( """
        select al from EventEntity al
        where al.icao = :icao
        order by al.id asc
        """
    )
    // @formatter:on
    List<EventEntity> getAllLogsForIcao(@Param("icao") String icao);

    // @formatter:off
    @Query( """
        select al from EventEntity al
        where al.icao = :icao and al.id > :logId
        order by al.id asc
        """
    )
    // @formatter:on
    List<EventEntity> getAllLogsForIcaoAfterLogId(@Param("icao") String icao, @Param("logId") Long logId);

    // @formatter:off
    @Query( """
        select al from EventEntity al
        where al.icao = :icao and al.id >= :beginLogId
        order by al.id asc
        """
    )
    // @formatter:on
    List<EventEntity> getLogsForIcao(@Param("icao") String icao, @Param("beginLogId") Long beginLogId);

    // @formatter:off
    @Query( """
        select al from EventEntity al
        where al.icao = :icao and al.id >= :beginLogId and al.id <= :endLogId
        order by al.id asc
        """
    )
    // @formatter:on
    List<EventEntity> getLogsForIcao(@Param("icao") String icao, @Param("beginLogId") Long beginLogId, @Param("endLogId") Long endLogId);
}
