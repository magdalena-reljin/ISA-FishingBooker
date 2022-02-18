package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;

import java.util.List;

public interface AdditionalServicesService {
    List<AdditionalServices> getAll();
    AdditionalServices findById(Long id);
}
