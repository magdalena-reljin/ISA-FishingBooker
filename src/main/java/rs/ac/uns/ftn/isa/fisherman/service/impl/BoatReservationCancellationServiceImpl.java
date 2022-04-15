package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.BoatReservationDto;
import rs.ac.uns.ftn.isa.fisherman.service.BoatReservationCancellationService;

@Service
public class BoatReservationCancellationServiceImpl implements BoatReservationCancellationService {


    @Override
    public boolean addCancellation(BoatReservationDto boatReservationDto) {
        return false;
    }

    @Override
    public boolean clientHasCancellationForAdventureInPeriod(BoatReservationDto boatReservationDto) {
        return false;
    }
}
