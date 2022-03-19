package rs.ac.uns.ftn.isa.fisherman.service;
import org.springframework.cache.annotation.Cacheable;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.List;
import java.util.Set;

public interface BoatService {
    void save(Boat boat);
    @Cacheable("boat")
    Boat findById(Long id);

    void addNewImage(String boatName, Image image);

    Set<Boat> findByOwnersId(Long id);

    Boat findByName(String name);

    Boat findByNameAndOwner(String boatName, Long boatOwner);

    void edit(Boat boat, Boolean deleteOldImages);

    void delete(Long id);

    List<Boat> findAll();
}
