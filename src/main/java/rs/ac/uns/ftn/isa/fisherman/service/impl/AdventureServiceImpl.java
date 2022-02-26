package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.repository.AdventureRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdditionalServicesService;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureService;
import rs.ac.uns.ftn.isa.fisherman.service.ImageService;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdventureServiceImpl implements AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private ImageService imageService;
    @Autowired
    private AdditionalServicesService additionalServicesService;

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

    @Override
    public void delete(Adventure adventure) {
        adventureRepository.delete(adventure);
    }

    @Override
    public void edit(Adventure adventure, Long instructorId) {
        Adventure oldAdventure= adventureRepository.findAdventureByName(adventure.getName(),instructorId);
        System.out.println("NASAOO SAMM STARII"+oldAdventure.getDescription());
        System.out.println("NASAOO SAMM NOVIII"+adventure.getDescription());
        for(AdditionalServices adventure1: adventure.getAdditionalServices())
           System.out.println("SERVISIIIIIIII *************************************************************"+ adventure1.getName()+ adventure1.getPrice());

        oldAdventure.setDescription(adventure.getDescription());
        oldAdventure.setAddress(adventure.getAddress());
        oldAdventure.setEquipment(adventure.getEquipment());
        oldAdventure.setCancelingCondition(adventure.getCancelingCondition());
        oldAdventure.setInstructorsBiography(adventure.getInstructorsBiography());
        oldAdventure.setRules(adventure.getRules());
        oldAdventure.setMaxPeople(adventure.getMaxPeople());
        oldAdventure.setPrice(adventure.getPrice());
        Set<AdditionalServices> oldAdditionalServices=oldAdventure.getAdditionalServices();
        Set<Image> oldImages= oldAdventure.getImages();
        oldAdventure.setAdditionalServices(adventure.getAdditionalServices());
        if(adventure.getImages()==null)  oldAdventure.setImages(new HashSet<>());
        adventureRepository.save(oldAdventure);
        Set<AdditionalServices> savedServices=adventureRepository.findAdventureByName(adventure.getName(),instructorId).getAdditionalServices();
        if(adventure.getImages()==null)   imageService.delete(oldImages);
        additionalServicesService.delete(findDeletedAdditionalServices(oldAdditionalServices,savedServices));
    }

    private Set<AdditionalServices> findDeletedAdditionalServices(Set<AdditionalServices> oldServices,Set<AdditionalServices> newServices){
        Set<AdditionalServices> deletedServices=new HashSet<>();
        boolean exits=false;
        for(AdditionalServices oldAdditionalService: oldServices) {
            for(AdditionalServices newAdditionalService: newServices){
                if (newAdditionalService.getId().equals(oldAdditionalService.getId()))
                    exits = true;
            }
            if(!exits)
                deletedServices.add(oldAdditionalService);
            exits=false;
        }
        return deletedServices;
    }
}
