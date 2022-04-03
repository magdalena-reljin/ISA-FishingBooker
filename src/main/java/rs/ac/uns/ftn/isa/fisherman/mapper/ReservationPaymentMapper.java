package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.model.BoatReservation;
import rs.ac.uns.ftn.isa.fisherman.model.Reservation;

public class ReservationPaymentMapper {

    public Reservation boatReservationToReservation(BoatReservation boatReservation){
        return  new Reservation(boatReservation.getId(),boatReservation.getStartDate(),boatReservation.getEndDate()
               ,boatReservation.getClient(), boatReservation.getPaymentInformation());
    }
}
