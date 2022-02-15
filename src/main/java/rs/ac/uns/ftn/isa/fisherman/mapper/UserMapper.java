package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.User;

public class UserMapper {

    public UserRequestDTO userToUserRequestDTO(User user){
        AddressMapper addressMapper=new AddressMapper();
        return new UserRequestDTO(user.getId(),user.getUsername(),user.getPassword(),user.getName(),
                user.getLastName(), user.getPhoneNum(),addressMapper.adressToDTO(user.getAddress()),"","");
    }

}
