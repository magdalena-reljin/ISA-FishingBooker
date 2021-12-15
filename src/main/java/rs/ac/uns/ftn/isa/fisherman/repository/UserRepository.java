package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email );
}
