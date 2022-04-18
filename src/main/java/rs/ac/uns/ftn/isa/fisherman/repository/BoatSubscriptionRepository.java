package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureSubscription;
import rs.ac.uns.ftn.isa.fisherman.model.BoatSubscription;

public interface BoatSubscriptionRepository extends JpaRepository<BoatSubscription, Long> {

    @Query(value="SELECT CASE WHEN  COUNT(b) > 0 THEN true ELSE false END FROM boat_subscription b where boat_id=:boat_id and users_id=:users_id",nativeQuery = true)
    boolean subscriptionExists(@Param("boat_id")Long boatId, @Param("users_id")Long usersId);

    @Query(value="SELECT * FROM boat_subscription where users_id=:users_id and boat_id=:boat_id",nativeQuery = true)
    BoatSubscription getSubscriptionOnBoat(@Param("boat_id")Long boatId, @Param("users_id")Long usersId);

}
