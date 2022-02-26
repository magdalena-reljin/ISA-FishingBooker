package rs.ac.uns.ftn.isa.fisherman.service;


import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

public interface AdventureService {

    void save(Adventure adventure);
    Adventure findByName(String cabinName);

    void addNewImage(String adventureName, Image image);

}
