package rs.ac.uns.ftn.isa.fisherman.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;

public interface CabinRepository extends JpaRepository<Cabin,Integer> {
    Cabin findById(Long id);
    Cabin findByName(String cabin);
}
