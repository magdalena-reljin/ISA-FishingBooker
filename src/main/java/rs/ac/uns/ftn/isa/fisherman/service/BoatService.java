package rs.ac.uns.ftn.isa.fisherman.service;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.Set;

public interface BoatService {
    void save(Boat boat);

    void addNewImage(String boatName, Image image);

    Set<Boat> findByOwnersId(Long id);

    Boat findByName(String name);

    Boat findByNameAndOwner(String boatName, Long boatOwner);

    void edit(Boat boat, Boolean deleteOldImages);

    void delete(Long id);
}
