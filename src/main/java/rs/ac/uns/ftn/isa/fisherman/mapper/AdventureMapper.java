package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AdventureDto;
import rs.ac.uns.ftn.isa.fisherman.model.Adventure;

public class AdventureMapper {

    AddressMapper addressMapper = new AddressMapper();
    ImageMapper imageMapper=new ImageMapper();
    AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    public Adventure AdventureDtoToAdventure(AdventureDto adventure){

        return  new Adventure(adventure.getId(),adventure.getName(),addressMapper.dtoToAddress(adventure.getAddress()),
                adventure.getDescription(),adventure.getInstructorsBiography(),adventure.getMaxPeople(),adventure.getPrice(),

                adventure.getRules(),adventure.getEquipment(),adventure.getCancelingCondition());
    }

    public AdventureDto AdventureToAdventureDto(Adventure adventure){
        return new AdventureDto(adventure.getId(),adventure.getName(),addressMapper.addressToDTO(adventure.getAddress())
        ,adventure.getDescription(),adventure.getInstructorsBiography(),imageMapper.ImageToImageDtoS(adventure.getImages()),

                adventure.getMaxPeople(),adventure.getPrice(),adventure.getRules(),adventure.getEquipment(),additionalServiceMapper.AdditionalServicesToAdditionalServiceDtos(adventure.getAdditionalServices()),
                adventure.getCancelingCondition(),adventure.getFishingInstructor().getUsername());
    }

    public Adventure AdventureDtoToEditAdventure(AdventureDto adventureDto){
            return new Adventure(adventureDto.getId(),adventureDto.getName(),addressMapper.dtoToAddress(adventureDto.getAddress()),
                    adventureDto.getDescription(),adventureDto.getInstructorsBiography(),imageMapper.ImageDtoSToImages(adventureDto.getImages()),
                    adventureDto.getMaxPeople(),adventureDto.getPrice(),adventureDto.getRules(),adventureDto.getEquipment(),additionalServiceMapper.AdditionalServicesDtoToAdditionalServices(adventureDto.getAdditionalServices()),
                    adventureDto.getCancelingCondition());
    }
}
