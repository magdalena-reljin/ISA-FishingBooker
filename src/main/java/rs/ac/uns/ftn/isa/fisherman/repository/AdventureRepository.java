package rs.ac.uns.ftn.isa.fisherman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;

import java.util.Set;


public interface AdventureRepository extends JpaRepository<Adventure, Long> {
    Adventure findByName(String cabinName);
    @Query(value = "SELECT * FROM adventure WHERE users_id=:users_id",nativeQuery = true)
    Set<Adventure> findAdventuresByInstructorId(@Param("users_id")Long id);

    @Query(value = "SELECT * FROM adventure WHERE users_id=:users_id and name=:name",nativeQuery = true)
    Adventure findAdventureByName(@Param("name")String name, @Param("users_id")Long users_id);

}
