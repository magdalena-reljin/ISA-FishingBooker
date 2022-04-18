package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.AdventureSubscription;

public interface AdventureSubscriptionRepository extends JpaRepository<AdventureSubscription, Long> {

    @Query(value="SELECT CASE WHEN  COUNT(a) > 0 THEN true ELSE false END FROM adventure_subscription a where adventure_id=:adventure_id and users_id=:users_id",nativeQuery = true)
    boolean subscriptionExists(@Param("adventure_id")Long adventureId, @Param("users_id")Long usersId);

    @Query(value="SELECT * FROM adventure_subscription where users_id=:users_id and adventure_id=:adventure_id",nativeQuery = true)
    AdventureSubscription getSubscriptionOnAdventure(@Param("adventure_id")Long adventureId,@Param("users_id")Long usersId);
}
