package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.Image;

public interface BoatService {
    void save(Boat boat);

    void addNewImage(String boatName, Image image);
}
