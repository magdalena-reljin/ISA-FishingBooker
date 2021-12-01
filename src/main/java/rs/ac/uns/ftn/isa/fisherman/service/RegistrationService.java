package rs.ac.uns.ftn.isa.fisherman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.repository.IUserRepository;

@Service
public class RegistrationService {
    @Autowired
    private IUserRepository userRepository;

    public void RegisterCabinOwner(CabinOwner cabinOwner){
        userRepository.save(cabinOwner);
    }
}
