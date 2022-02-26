package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;

public class AdventureMapper {

    AddressMapper addressMapper = new AddressMapper();
    ImageMapper imageMapper=new ImageMapper();
    AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    public Adventure AdventureDtoToAdventure(AdventureDto adventure){

        return  new Adventure(adventure.getId(),adventure.getName(),addressMapper.dtotoaddress(adventure.getAddress()),
                adventure.getDescription(),adventure.getInstructorsBiography(),adventure.getMaxPeople(),adventure.getPrice(),
                adventure.getRules(),adventure.getEquipment(),adventure.getCancelingCondition());
    }

    public AdventureDto AdventureToAdventureDto(Adventure adventure){
        return new AdventureDto(adventure.getId(),adventure.getName(),addressMapper.adressToDTO(adventure.getAddress())
        ,adventure.getDescription(),adventure.getInstructorsBiography(),imageMapper.ImageToImageDtos(adventure.getImages()),
                adventure.getMaxPeople(),adventure.getPrice(),adventure.getRules(),adventure.getEquipment(),additionalServiceMapper.AdditionalServicesToAdditionalServiceDtos(adventure.getAdditionalServices()),
                adventure.getCancelingCondition(),adventure.getFishingInstructor().getUsername());
    }
}
