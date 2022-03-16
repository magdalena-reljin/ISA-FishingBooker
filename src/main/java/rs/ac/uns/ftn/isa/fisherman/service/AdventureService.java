package rs.ac.uns.ftn.isa.fisherman.service;


import org.springframework.cache.annotation.Cacheable;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.List;
import java.util.Set;
public interface AdventureService {

    void save(Adventure adventure);
    Adventure findByName(String adventureName);

    void addNewImage(String adventureName, Image image);
    
    Set<Adventure> findAdventuresByInstructorId(Long id);
    Adventure findAdventureByName(String adventureName, Long fishingInstructorId);

    void delete(Long id);

    void edit(Adventure adventure, Long id);

    List<Adventure> findAll();
}
