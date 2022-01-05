package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;

import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;


public class BoatOwnerMapper {

    public BoatOwner userRequestDtoToBoatOwner(UserRequestDTO userRequest){
        AddressMapper addressMapper=new AddressMapper();
        return new BoatOwner(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getEmail(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtotoaddress(userRequest.getAddress()),userRequest.getRegistrationReason());
    }

    public UserRequestDTO boatOwnerToUserRequestDto(BoatOwner boatOwner){

        return new UserRequestDTO(boatOwner.getEmail(),boatOwner.getName(),boatOwner.getLastName(),boatOwner.getRoleApp(),boatOwner.getRegistrationReason());
    }

}