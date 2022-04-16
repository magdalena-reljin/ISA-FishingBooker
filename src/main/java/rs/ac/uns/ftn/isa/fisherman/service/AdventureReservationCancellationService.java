package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;

public interface AdventureReservationCancellationService {

    boolean addCancellation(AdventureReservationDto adventureReservationDto);

    boolean clientHasCancellationWithInstructorInPeriod(AdventureReservationDto adventureReservationDto);

}
