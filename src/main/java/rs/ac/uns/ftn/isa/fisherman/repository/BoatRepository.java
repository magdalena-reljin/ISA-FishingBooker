package rs.ac.uns.ftn.isa.fisherman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;

import java.util.Set;

public interface BoatRepository extends JpaRepository<Boat,Integer> {
    Boat findById(Long id);
    Boat findByName(String boat);
    @Query(value = "SELECT * FROM boat WHERE users_id=:users_id",nativeQuery = true)
    Set<Boat> findByOwnersId(@Param("users_id")Long id);
}
