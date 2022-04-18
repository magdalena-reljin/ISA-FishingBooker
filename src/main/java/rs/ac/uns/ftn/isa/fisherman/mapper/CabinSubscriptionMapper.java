package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinSubscriptionDto;
import rs.ac.uns.ftn.isa.fisherman.model.CabinSubscription;

public class CabinSubscriptionMapper {

    final private CabinMapper cabinMapper = new CabinMapper();

    public CabinSubscriptionDto cabinSubscriptionToCabinSubscriptionDto(CabinSubscription cabinSubscription) {
        return new CabinSubscriptionDto(cabinSubscription.getId(), cabinSubscription.getClient().getUsername(), cabinMapper.cabinToCabinDto(cabinSubscription.getCabin()));
    }
}
