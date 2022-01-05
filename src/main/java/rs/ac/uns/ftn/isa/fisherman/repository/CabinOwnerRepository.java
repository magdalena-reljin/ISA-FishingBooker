package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;

import java.util.List;

public interface CabinOwnerRepository extends JpaRepository<CabinOwner,Integer> {
    @Query(value="SELECT role,id,accepted,activation_url,city,country,latitude,longitude,street_and_num,email,enabled,last_name,last_password_reset_date,name,password,phone_num,registration_reason,is_predefined FROM users where role='CABIN OWNER' and enabled=false",nativeQuery = true)
    List<CabinOwner> getNewCabinOwners();
}
