package rs.ac.uns.ftn.isa.fisherman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableCabinPeriod;

import java.time.LocalDateTime;
import java.util.Set;

public interface AvailableCabinPeriodRepository extends JpaRepository<AvailableCabinPeriod, Long> {
    @Query(value="SELECT * FROM available_period where cabin_id=:cabin_id",nativeQuery = true)
    Set<AvailableCabinPeriod> findByCabinId(@Param("cabin_id")Long cabinId);

    @Query(value="SELECT CASE WHEN  COUNT(ap) > 0 THEN true ELSE false END FROM available_period ap where cabin_id=:cabin_id and ((:start between start_date and end_date)) and ((:end between start_date and end_date))",nativeQuery = true)
    boolean cabinIsAvailable(@Param("cabin_id")Long cabinId,@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
}
