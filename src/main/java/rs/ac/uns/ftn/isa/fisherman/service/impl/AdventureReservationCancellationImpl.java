package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.AdventureReservationDto;
import rs.ac.uns.ftn.isa.fisherman.service.AdventureReservationCancellationService;

@Service
public class AdventureReservationCancellationImpl implements AdventureReservationCancellationService {


    @Override
    public boolean addCancellation(AdventureReservationDto adventureReservationDto) {
        return false;
    }

    @Override
    public boolean clientHasCancellationForAdventureInPeriod(AdventureReservationDto adventureReservationDto) {
        return false;
    }
}
