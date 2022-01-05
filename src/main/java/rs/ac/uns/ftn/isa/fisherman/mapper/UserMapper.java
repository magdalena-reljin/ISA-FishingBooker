package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.User;

public class UserMapper {

    public UserRequestDTO userToUserRequestDTO(User user){
        System.out.println("USER ROLE"+ user.getRoleApp());
        //UserRequestDTO userD= new UserRequestDTO(user.getEmail(),user.getName(),user.getLastName(),user.getRoleApp());
       // System.out.println("DTO ROLE"+ userD.getRole());
        return  null;
    }
}
