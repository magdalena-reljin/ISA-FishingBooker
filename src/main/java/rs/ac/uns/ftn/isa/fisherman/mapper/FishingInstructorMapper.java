package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;

import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;

import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;

public class FishingInstructorMapper {
    public FishingInstructor userRequestDtoToFishingInstructor(UserRequestDTO userRequest){
        AddressMapper addressMapper=new AddressMapper();
        return new FishingInstructor(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getEmail(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtotoaddress(userRequest.getAddress()),userRequest.getRegistrationReason());
    }

    public UserRequestDTO fishingInstructorToUserRequestDto(FishingInstructor fishingInstructor){

        return new UserRequestDTO(fishingInstructor.getEmail(),fishingInstructor.getName(),fishingInstructor.getLastName(),fishingInstructor.getRoleApp(),fishingInstructor.getRegistrationReason());
    }

}