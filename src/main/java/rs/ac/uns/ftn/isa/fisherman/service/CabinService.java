package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.List;
import java.util.Set;

public interface CabinService {
     Cabin findById(Long id);
     List<Cabin> findAll();
    Cabin findByName(String cabin);
    void save(Cabin cabin);

    void addNewImage(String cabinName, Image image);

    Set<Cabin> findByOwnersId(Long id);

    void delete(Cabin cabin);

    void edit(Cabin cabin, Boolean deleteOldImages);
}
