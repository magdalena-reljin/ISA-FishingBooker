package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;

public class AdventureReservationMapper {
    private AdventureMapper adventureMapper= new AdventureMapper();
    private AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();

    public AdventureReservation adventureReservationDtoToAdventureReservation(AdventureReservationDto adventureReservationDto,FishingInstructor fishingInstructor){
       Adventure adventure = adventureMapper.adventureDtoToAdventure(adventureReservationDto.getAdventureDto());
        return new AdventureReservation(adventureReservationDto.getId(),adventureReservationDto.getStartDate(),
                adventureReservationDto.getEndDate(),adventureReservationDto.getPrice(),null,adventure,fishingInstructor,
                additionalServiceMapper.additionalServicesDtoToAdditionalServices(adventureReservationDto.getAddedAdditionalServices()));
    }
    public AdventureReservationDto adventureReservationToAdventureReservationDto(AdventureReservation adventureReservation){
        String fullName=adventureReservation.getClient().getName()+" "+adventureReservation.getClient().getLastName();
        return new AdventureReservationDto(adventureReservation.getId(),
                adventureReservation.getStartDate(),adventureReservation.getEndDate(),
                adventureReservation.getPrice(),adventureReservation.getClient().getUsername(),fullName,
                adventureMapper.adventureToAdventureDto(adventureReservation.getAdventure()),additionalServiceMapper
                .additionalServicesToAdditionalServiceDtoS(adventureReservation.getAddedAdditionalServices()));
    }


}
