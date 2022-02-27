package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Boat;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.repository.BoatRepository;
import rs.ac.uns.ftn.isa.fisherman.service.BoatService;

import java.util.Set;

@Service
public class BoatServiceImpl implements BoatService {
    @Autowired
    private BoatRepository boatRepository;

    @Override
    public void save(Boat boat) {
        boatRepository.save(boat);
    }

    @Override
    public void addNewImage(String boatName, Image image) {
        Boat boat= boatRepository.findByName(boatName);
        Set<Image> currentImages=boat.getImages();
        currentImages.add(image);
        boatRepository.save(boat);
    }

    @Override
    public Set<Boat> findByOwnersId(Long id) {
        return boatRepository.findByOwnersId(id);
    }

    @Override
    public Boat findByName(String name) {
        return boatRepository.findByName(name);
    }



    @Override
    public Boat findByNameAndOwner(String boatName, Long boatOwner) {
        return boatRepository.findByNameAndOwner(boatName, boatOwner);
    }
}
