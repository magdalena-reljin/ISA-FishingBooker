package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.model.*;


public class BoatReservationMapper {
    private  BoatMapper boatMapper=new BoatMapper();
    private AdditionalServiceMapper additionalServiceMapper=new AdditionalServiceMapper();
    private PaymentInformationMapper paymentInformationMapper=new PaymentInformationMapper();

    public BoatReservation boatReservationOwnerDtoToBoatReservation(BoatReservationDto boatReservationDto){
        Boat boat=boatMapper.boatDtoToBoat(boatReservationDto.getBoatDto());
        return new BoatReservation(boatReservationDto.getId(),boatReservationDto.getStartDate(),
                boatReservationDto.getEndDate(),null,paymentInformationMapper.dtoToPaymentInformation(boatReservationDto.getPaymentInformationDto()),boat,
                additionalServiceMapper.additionalServicesDtoToAdditionalServices(boatReservationDto.getAddedAdditionalServices()),
                boatReservationDto.getNeedsCaptainServices());
    }
    public BoatReservationDto boatReservationToBoatReservationDto(BoatReservation boatReservation){
        String fullName=boatReservation.getClient().getName()+" "+boatReservation.getClient().getLastName();
        return new BoatReservationDto(boatReservation.getId(),boatReservation.getStartDate(),
                boatReservation.getEndDate(),boatReservation.getClient().getUsername(),
                fullName,paymentInformationMapper.paymentInformationToDto(boatReservation.getPaymentInformation()),boatReservation.isSuccessfull(),boatMapper.boatToBoatDto(boatReservation.getBoat()),
                additionalServiceMapper.additionalServicesToAdditionalServiceDtoS(boatReservation.getAddedAdditionalServices()),
                boatReservation.getNeedsCaptainService());
    }


}
