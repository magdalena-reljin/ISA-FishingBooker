package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureService;

import java.util.Set;

@Service
public class AdventureServiceImpl implements AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    public void save(Adventure adventure){
       adventureRepository.save(adventure);
    }

    @Override
    public Adventure findByName(String adventureName) {
        return adventureRepository.findByName(adventureName);
    }

    @Override
    public void addNewImage(String adventureName, Image image) {
        Adventure adventure= adventureRepository.findByName(adventureName);
        Set<Image> currentImages=adventure.getImages();
        currentImages.add(image);
        adventureRepository.save(adventure);
    }
    public Set<Adventure> findAdventuresByInstructorId(Long id){
        return adventureRepository.findAdventuresByInstructorId(id);
    }

    @Override
    public Adventure findAdventureByName(String name, Long fishingInstructorId) {
        return adventureRepository.findAdventureByName(name,fishingInstructorId);
    }
}
