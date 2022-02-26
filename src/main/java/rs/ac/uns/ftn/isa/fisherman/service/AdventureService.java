package rs.ac.uns.ftn.isa.fisherman.service;


import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

import java.util.Set;

public interface AdventureService {

    void save(Adventure adventure);
    Adventure findByName(String cabinName);

    void addNewImage(String adventureName, Image image);
    Set<Adventure> findAdventuresByInstructorId(Long id);

    Adventure findAdventureByName(String adventureName, Long fishingInstructorId);
}
