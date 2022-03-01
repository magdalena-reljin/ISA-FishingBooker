package rs.ac.uns.ftn.isa.fisherman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;

import java.util.List;

public interface BoatOwnerRepository extends JpaRepository<BoatOwner,Integer> {
    @Query(value="SELECT * FROM users where role='BOAT OWNER' and enabled=false",nativeQuery = true)
    List<BoatOwner> getNewBoatOwners();

    @Query(value="SELECT * FROM users where role='BOAT OWNER' and enabled=true",nativeQuery = true)
    List<BoatOwner> getActiveBoatOwners();

    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    BoatOwner findByUsername(@Param("username")String username);



}
