package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;

public class AdventureReservationMapper {
    private AdventureMapper adventureMapper= new AdventureMapper();
    private AdditionalServiceMapper additionalServiceMapper = new AdditionalServiceMapper();
    public AdventureReservation adventureReservationDtoToAdventureReservation(AdventureReservationDto adventureReservationDto){
       Adventure adventure = adventureMapper.adventureDtoToAdventure(adventureReservationDto.getAdventureDto());
        return new AdventureReservation(adventureReservationDto.getId(),adventureReservationDto.getStartDate(),
                adventureReservationDto.getEndDate(),adventureReservationDto.getPrice(),null,adventure,null,
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
   /* public BoatReservationDto quickBoatReservationToBoatReservationDto(QuickReservationBoat quickReservationBoat) {
        Client client=new Client();
        String fullName=null;
        if(quickReservationBoat.getClient() !=null){
            client.setUsername(quickReservationBoat.getClient().getUsername());
            fullName=quickReservationBoat.getClient().getName()+" "+quickReservationBoat.getClient().getLastName();
        }

        return new BoatReservationDto(quickReservationBoat.getId(),quickReservationBoat.getStartDate(),
                quickReservationBoat.getEndDate(),quickReservationBoat.getPrice(),client.getUsername(),
                fullName,boatMapper.boatToBoatDto(quickReservationBoat.getBoat()),
                additionalServiceMapper.additionalServicesToAdditionalServiceDtoS(quickReservationBoat.getAddedAdditionalServices()),
                quickReservationBoat.getNeedsCaptainServices());
    }*/
}
