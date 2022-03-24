package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AvailableBoatOwnerPeriod;

import java.util.Set;

public interface AvailableBoatOwnerPeriodRepository extends JpaRepository<AvailableBoatOwnerPeriod,Long> {
    @Query(value="SELECT * FROM available_period where users_id=:users_id and dtype= 'AvailableBoatOwnerPeriod'",nativeQuery = true)
    Set<AvailableBoatOwnerPeriod> findByOwnersId(@Param("users_id")Long usersId);
}
