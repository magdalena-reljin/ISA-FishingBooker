package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.User;

public class UserMapper {

    public UserRequestDTO userToUserRequestDTO(User user){
        System.out.println("USER ROLE"+ user.getRoleApp());
        AddressMapper addressMapper=new AddressMapper();
        return new UserRequestDTO(user.getId(),user.getEmail(),user.getPassword(),user.getName(),
                user.getLastName(), user.getPhoneNum(),addressMapper.adressToDTO(user.getAddress()),"","");
    }

}
