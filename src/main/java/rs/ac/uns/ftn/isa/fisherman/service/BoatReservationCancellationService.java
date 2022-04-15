package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;

public interface BoatReservationCancellationService {

    boolean addCancellation(BoatReservationDto boatReservationDto);

    boolean clientHasCancellationForAdventureInPeriod(BoatReservationDto boatReservationDto);

}
