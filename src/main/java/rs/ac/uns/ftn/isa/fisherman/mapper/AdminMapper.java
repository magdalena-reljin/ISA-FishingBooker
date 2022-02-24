package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.Admin;
import rs.ac.uns.ftn.isa.fisherman.model.User;

public class AdminMapper {

    AddressMapper addressMapper=new AddressMapper();
    public Admin userRequestDtoToAdmin(UserRequestDTO user){
        return new Admin(user.getId(),user.getFirstname(),user.getLastname(),user.getUsername(),user.getPassword(),user.getPhoneNum(),addressMapper.dtotoaddress(user.getAddress()),false);
    }

    public  UserRequestDTO adminToUserRequestDTO(Admin admin){
        return new UserRequestDTO(admin.getId(), admin.getUsername(),admin.getPassword(),admin.getName(),admin.getLastName(),admin.getPhoneNum(),addressMapper.adressToDTO(admin.getAddress()),"",admin.getRoleApp());
    }
}