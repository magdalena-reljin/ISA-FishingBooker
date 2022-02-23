package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.List;

public interface CabinService {
     Cabin findById(Long id);
     List<Cabin> findAll();
    Cabin findByName(String cabin);
    void save(Cabin cabin);

    void addNewImage(String cabinName, Image image);
}
