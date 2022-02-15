package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.CabinOwnerDTO;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;

public class CabinOwnerMapper {

    public CabinOwnerDTO cabinOwnerToDTO(CabinOwner cabinOwner){
        return new CabinOwnerDTO(cabinOwner.getId(), cabinOwner.getName(), cabinOwner.getLastName(), cabinOwner.getUsername(),
                cabinOwner.getPassword(), cabinOwner.getPhoneNum(), cabinOwner.getAddress(), cabinOwner.getRegistrationReason());
    }
    public CabinOwner dtotocabinowner(CabinOwnerDTO cabinOwnerDTO){
        return new CabinOwner(cabinOwnerDTO.getId(), cabinOwnerDTO.getName(), cabinOwnerDTO.getLastName(), cabinOwnerDTO.getUsername(),
                cabinOwnerDTO.getPassword(), cabinOwnerDTO.getPhoneNum(), cabinOwnerDTO.getAddress(), cabinOwnerDTO.getRegistrationReason());
    }

    public CabinOwner userRequestDTOToCabinOwner(UserRequestDTO userRequest) {
        AddressMapper addressMapper=new AddressMapper();


       CabinOwner cabinOwner = new CabinOwner(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getUsername(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtotoaddress(userRequest.getAddress()),userRequest.getRegistrationReason());
        System.out.println("AAAAAAAAAAAAAAAAAAAAA"+  cabinOwner.getPassword());
        return cabinOwner;
    }
    public UserRequestDTO CabinOwnerToUserRequestDto(CabinOwner cabinOwner){

       return new UserRequestDTO(cabinOwner.getUsername(),cabinOwner.getName(),cabinOwner.getLastName(),cabinOwner.getRoleApp(),cabinOwner.getRegistrationReason());
    }
}
