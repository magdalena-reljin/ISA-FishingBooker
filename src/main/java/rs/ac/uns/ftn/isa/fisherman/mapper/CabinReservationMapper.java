package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinDto;
import rs.ac.uns.ftn.isa.fisherman.dto.CabinReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.Client;

public class CabinReservationMapper {

    CabinMapper cabinMapper = new CabinMapper();

    public CabinReservation CabinReservationDtoToCabinReservation(CabinReservationDto cabinReservationDto){
        Cabin cabin = cabinMapper.CabinDtoToCabin(cabinReservationDto.getCabinDto());
        CabinReservation cabinReservation = new CabinReservation(cabinReservationDto.getId(), cabinReservationDto.getStartDate(),cabinReservationDto.getEndDate(), cabinReservationDto.getPrice(), new Client(), cabin, null);
        return cabinReservation;
    }
}
