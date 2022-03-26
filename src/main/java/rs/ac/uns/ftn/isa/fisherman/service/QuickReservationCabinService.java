package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.CabinReservation;
import rs.ac.uns.ftn.isa.fisherman.model.QuickReservationCabin;

import java.time.LocalDateTime;
import java.util.Set;

public interface QuickReservationCabinService {
    boolean ownerCreates(CabinReservation cabinReservation);


    Set<QuickReservationCabin> getByCabinId(Long cabinId);

    boolean quickReservationExists(Long id, LocalDateTime startDate, LocalDateTime endDate);
}
