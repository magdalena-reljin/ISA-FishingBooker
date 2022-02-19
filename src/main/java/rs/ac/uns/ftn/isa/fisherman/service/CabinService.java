package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.Cabin;

import java.util.List;

public interface CabinService {
     Cabin findById(Long id);
     List<Cabin> findAll();
    Cabin findByName(String cabin);

}
