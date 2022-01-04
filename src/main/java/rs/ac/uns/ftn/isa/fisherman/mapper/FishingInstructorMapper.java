package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;

import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;

public class FishingInstructorMapper {
    public FishingInstructor userRequestDtoToFishingInstructor(UserRequestDTO userRequest){
        AddressMapper addressMapper=new AddressMapper();
        return new FishingInstructor(userRequest.getId(),userRequest.getFirstname(),userRequest.getLastname(),userRequest.getEmail(),
                userRequest.getPassword(),userRequest.getPhoneNum(),addressMapper.dtotoaddress(userRequest.getAddress()),userRequest.getRegistrationReason());
    }
}
