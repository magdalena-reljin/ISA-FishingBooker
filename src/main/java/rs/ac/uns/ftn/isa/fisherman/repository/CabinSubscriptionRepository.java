package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;

import java.util.Set;

public interface CabinSubscriptionRepository extends JpaRepository<CabinSubscription, Long> {

    @Query(value="SELECT * FROM cabin_subscriptions where users_id=:user_id) ",nativeQuery = true)
    Set<CabinSubscription> getClientSubscriptions(@Param("user_id")Long user_id);

}
