package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;
import rs.ac.uns.ftn.isa.fisherman.repository.AdditionalServicesRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AdditionalServicesService;

import java.util.List;

@Service
public class AdditionalServicesServiceImpl implements AdditionalServicesService {
    @Autowired
    private AdditionalServicesRepository additionalServicesRepository;
    public List<AdditionalServices> getAll(){
        return additionalServicesRepository.findAll();
    }
    public AdditionalServices findById(Long id){
        return additionalServicesRepository.findById(id);
    }
}
