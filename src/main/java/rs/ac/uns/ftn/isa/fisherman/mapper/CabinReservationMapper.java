package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;

public class CabinReservationMapper {

    private final CabinMapper cabinMapper = new CabinMapper();
    private final AdditionalServiceMapper additionalServicesMapper=new AdditionalServiceMapper();
    public CabinReservation cabinReservationDtoToCabinReservation(CabinReservationDto cabinReservationDto){
        Cabin cabin = cabinMapper.cabinDtoToCabin(cabinReservationDto.getCabinDto());
        CabinReservation cabinReservation = new CabinReservation(cabinReservationDto.getId(),
                cabinReservationDto.getStartDate(),cabinReservationDto.getEndDate(), cabinReservationDto.getPrice(),
                new Client(), cabin, null);
        return cabinReservation;
    }
    public CabinReservation cabinOwnerReservationDtoToCabinReservation(CabinReservationDto cabinReservationDto){
        Cabin cabin = cabinMapper.cabinDtoToCabin(cabinReservationDto.getCabinDto());
        CabinReservation cabinReservation = new CabinReservation(cabinReservationDto.getId(),
                cabinReservationDto.getStartDate(),cabinReservationDto.getEndDate(), cabinReservationDto.getPrice(),
                new Client(), cabin, additionalServicesMapper.additionalServicesDtoToAdditionalServices(cabinReservationDto.getAddedAdditionalServices()));
        return cabinReservation;
    }

    public CabinReservationDto cabinReservationToCabinReservationDto(CabinReservation cabinReservation){
        String fullName=cabinReservation.getClient().getName()+" "+cabinReservation.getClient().getLastName();
        return new CabinReservationDto(cabinReservation.getId(),cabinReservation.getStartDate(),
                cabinReservation.getEndDate(),cabinReservation.getPrice(),cabinReservation.getClient().getUsername(),
                fullName,null,null);
    }

    public CabinReservationDto quickCabinReservationToCabinReservationDto(QuickReservationCabin quickReservationCabin) {
        Client client=new Client();
        String fullName=null;
        if(quickReservationCabin.getClient() !=null){
            client.setUsername(quickReservationCabin.getClient().getUsername());
            fullName=quickReservationCabin.getClient().getName()+" "+quickReservationCabin.getClient().getLastName();
        }
        return new CabinReservationDto(quickReservationCabin.getId(),quickReservationCabin.getStartDate(),
                quickReservationCabin.getEndDate(),quickReservationCabin.getPrice(),client.getUsername(),
                fullName,null,null);
    }
}
