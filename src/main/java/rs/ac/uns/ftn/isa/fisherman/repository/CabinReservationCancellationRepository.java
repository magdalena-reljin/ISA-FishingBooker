package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservationCancellation;

public interface CabinReservationCancellationRepository extends JpaRepository<CabinReservationCancellation, Long> {
}
