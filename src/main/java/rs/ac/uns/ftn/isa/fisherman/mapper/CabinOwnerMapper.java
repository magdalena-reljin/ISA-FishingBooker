package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinOwnerDTO;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;

public class CabinOwnerMapper {

    public CabinOwnerDTO ModelToDTO(CabinOwner cabinOwner){
        return new CabinOwnerDTO(cabinOwner.getId(), cabinOwner.getName(), cabinOwner.getLastName(), cabinOwner.getEmail(),
                cabinOwner.getPassword(), cabinOwner.getPhoneNum(), cabinOwner.getAddress(), cabinOwner.getRegistrationReason());
    }
    public CabinOwner DTOToModel(CabinOwnerDTO cabinOwnerDTO){
        return new CabinOwner(cabinOwnerDTO.getId(), cabinOwnerDTO.getName(), cabinOwnerDTO.getLastName(), cabinOwnerDTO.getEmail(),
                cabinOwnerDTO.getPassword(), cabinOwnerDTO.getPhoneNum(), cabinOwnerDTO.getAddress(), cabinOwnerDTO.getRegistrationReason());
    }
}
