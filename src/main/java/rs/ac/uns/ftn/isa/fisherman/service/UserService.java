package rs.ac.uns.ftn.isa.fisherman.service;


import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;

import rs.ac.uns.ftn.isa.fisherman.model.BoatOwner;
import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.model.FishingInstructor;
import rs.ac.uns.ftn.isa.fisherman.model.User;

import javax.mail.MessagingException;
import java.util.List;


public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
    User registerCabinOwner(CabinOwner cabinOwner, String sourceURL) throws MessagingException;
    User registerBoatOwner(BoatOwner boatOwner, String sourceURL) throws MessagingException;
    User registerFishingInstructor(FishingInstructor fishingInstructor, String sourceURL) throws MessagingException;
    String findRoleById(Long id);

    User activateAccount(String email, String code);

    void acceptAccount(User user);
    void denyAccount(User user,String reason);

    void editUser(UserRequestDTO userRequest);
}