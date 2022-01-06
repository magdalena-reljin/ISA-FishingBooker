package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;

import java.util.List;

public interface FishingInstructorRepository extends JpaRepository<FishingInstructor,Integer> {
    @Query(value="SELECT role,id,activation_url,city,country,latitude,longitude,street_and_num,email,enabled,last_name,last_password_reset_date,name,password,phone_num,registration_reason,is_predefined FROM users where role='FISHING INSTRUCTOR' and enabled=false",nativeQuery = true)
    List<FishingInstructor> getNewFishingInstructor();
}
