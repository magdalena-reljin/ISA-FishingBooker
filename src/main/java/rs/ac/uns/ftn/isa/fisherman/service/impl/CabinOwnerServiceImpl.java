package rs.ac.uns.ftn.isa.fisherman.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinOwnerRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinOwnerService;

import java.util.List;

@Service
public class CabinOwnerServiceImpl implements CabinOwnerService {
    private CabinOwnerRepository cabinOwnerRepository;
    @Autowired
    public CabinOwnerServiceImpl(CabinOwnerRepository cabinOwnerRepository) {
        this.cabinOwnerRepository = cabinOwnerRepository;
    }

    public List<CabinOwner> getNewCabinOwners(){
        return cabinOwnerRepository.getNewCabinOwners();
    }

    @Override
    public List<CabinOwner> getActiveCabinOwners() {return cabinOwnerRepository.getActiveCabinOwners();}

    @Override
    public CabinOwner findByUsername(String username) {
        return cabinOwnerRepository.findByUsername(username);
    }

}
