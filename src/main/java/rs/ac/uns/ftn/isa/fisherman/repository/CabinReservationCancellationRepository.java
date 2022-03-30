package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservationCancellation;

import java.util.List;

public interface CabinReservationCancellationRepository extends JpaRepository<CabinReservationCancellation, Long> {

    @Query(value="SELECT * FROM cabin_reservation_cancellation where users_id=:id",nativeQuery = true)
    List<CabinReservationCancellation> getByUsersId(@Param("id")Long id);
}
