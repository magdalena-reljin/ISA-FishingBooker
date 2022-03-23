package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import java.util.List;

public interface CabinOwnerService {
    List<CabinOwner> getNewCabinOwners();
    List<CabinOwner> getActiveCabinOwners();

    CabinOwner findByUsername(String ownerUsername);
}
