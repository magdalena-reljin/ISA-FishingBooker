package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.dto.QuickReservationBoatDto;

public interface BoatReservationCancellationService {

    boolean addCancellation(BoatReservationDto boatReservationDto);

    boolean clientHasCancellationForBoatInPeriod(BoatReservationDto boatReservationDto);

    boolean addCancellationQuickReservation(QuickReservationBoatDto quickReservationBoatDto);
}
