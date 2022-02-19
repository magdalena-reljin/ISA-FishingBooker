package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;

import java.util.List;

@Service
public class CabinServiceImpl implements CabinService {
    @Autowired
    private CabinRepository cabinRepository;

    public Cabin findById(Long id){
        return cabinRepository.findById(id);
    }
    public List<Cabin> findAll(){
        return cabinRepository.findAll();
    }

    @Override
    public Cabin findByName(String cabin) {
        return cabinRepository.findByName(cabin);
    }
}
