package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.BoatSubscriptionDto;
import rs.ac.uns.ftn.isa.fisherman.model.BoatSubscription;

public class BoatSubscriptionMapper {

    final private BoatMapper boatMapper = new BoatMapper();

    public BoatSubscriptionDto boatSubscriptionToBoatSubscriptionDto(BoatSubscription boatSubscription) {
        return new BoatSubscriptionDto(boatSubscription.getId(), boatSubscription.getClient().getUsername(), boatMapper.boatToBoatDto(boatSubscription.getBoat()));
    }
}
