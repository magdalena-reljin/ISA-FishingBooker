package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;

import java.util.List;

public interface CabinOwnerRepository extends JpaRepository<CabinOwner,Integer> {
    @Query(value="SELECT * FROM users where role='CABIN OWNER' and enabled=false",nativeQuery = true)
    List<CabinOwner> getNewCabinOwners();

    @Query(value="SELECT * FROM users where role='CABIN OWNER' and enabled=true",nativeQuery = true)
    List<CabinOwner> getActiveCabinOwners();

    @Query(value="SELECT * FROM users where username=:username",nativeQuery = true)
    CabinOwner findByUsername(@Param("username")String username);
}
