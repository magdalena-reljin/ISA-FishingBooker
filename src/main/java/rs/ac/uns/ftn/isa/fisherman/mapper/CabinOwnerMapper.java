package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinOwnerDTO;
import rs.ac.uns.ftn.isa.fisherman.dto.RegistrationDTO;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequest;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;

public class CabinOwnerMapper {
    public CabinOwner RegistrationDTOToCabinOwner (RegistrationDTO registrationDTO){
        AddressMapper addressMapper=new AddressMapper();
        return new CabinOwner(null, registrationDTO.getName(), registrationDTO.getLastName(), registrationDTO.getEmail(),
                registrationDTO.getPassword(), registrationDTO.getPhoneNum(), addressMapper.DTOToAddress(registrationDTO.getAddress()), registrationDTO.getRegistrationReason());
    }
    public CabinOwnerDTO CabinOwnerToDTO (CabinOwner cabinOwner){
        return new CabinOwnerDTO(cabinOwner.getId(), cabinOwner.getName(), cabinOwner.getLastName(), cabinOwner.getEmail(),
                cabinOwner.getPassword(), cabinOwner.getPhoneNum(), cabinOwner.getAddress(), cabinOwner.getRegistrationReason());
    }
    public CabinOwner DTOToCabinOwner(CabinOwnerDTO cabinOwnerDTO){
        return new CabinOwner(cabinOwnerDTO.getId(), cabinOwnerDTO.getName(), cabinOwnerDTO.getLastName(), cabinOwnerDTO.getEmail(),
                cabinOwnerDTO.getPassword(), cabinOwnerDTO.getPhoneNum(), cabinOwnerDTO.getAddress(), cabinOwnerDTO.getRegistrationReason());
    }

    public CabinOwner UserRequestDTOToCabinOwner(UserRequest userRequest) {
        AddressMapper addressMapper=new AddressMapper();
        return  new CabinOwner(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getEmail(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.DTOToAddress(userRequest.getAddress()),userRequest.getRegistrationReason());
    }
}
