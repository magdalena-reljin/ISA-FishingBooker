package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.Cabin;
import rs.ac.uns.ftn.isa.fisherman.model.Image;
import rs.ac.uns.ftn.isa.fisherman.repository.CabinRepository;
import rs.ac.uns.ftn.isa.fisherman.service.CabinService;

import java.util.List;
import java.util.Set;

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

    @Override
    public void save(Cabin cabin) {
        cabinRepository.save(cabin);
    }

    @Override
    public void addNewImage(String cabinName, Image image) {
        Cabin cabin= cabinRepository.findByName(cabinName);
        Set<Image> currentImages=cabin.getImages();
        currentImages.add(image);
        cabinRepository.save(cabin);
    }

    @Override
    public Set<Cabin> findByOwnersId(Long id) {
        return cabinRepository.findByOwnersId(id);
    }
}
