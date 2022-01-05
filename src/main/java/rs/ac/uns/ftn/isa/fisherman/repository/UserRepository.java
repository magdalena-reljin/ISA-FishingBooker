package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email );
    @Query(value = "SELECT email,name,last_name,role,registration_reason FROM users WHERE role != 'ADMIN'  AND enabled=false AND accepted=false",nativeQuery = true)
    List<User> getNewUsers();
}
