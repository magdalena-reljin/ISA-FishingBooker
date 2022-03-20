package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import javax.mail.MessagingException;
import java.util.List;


public interface UserService {
    User findByUsername(String username);
    List<User> findAll ();
    User registerCabinOwner(CabinOwner cabinOwner) throws MessagingException;
    User registerBoatOwner(BoatOwner boatOwner) throws MessagingException;
    User registerClient(Client client) throws MessagingException;

    User registerAdmin(Admin admin);
    User registerFishingInstructor(FishingInstructor fishingInstructor) throws MessagingException;


    User activateAccount(String email, String code);

    void acceptAccount(User user);
    void denyAccount(User user,String reason);

    void editUser(UserRequestDTO userRequest);

    void deleteUser(User user);

    void saveDeleteAccountRequest(String username, String reasonForDeleting);

    List<User> getAllRequestsForDeletingAccount();

    void sendDenyReason(String response, String recipient) throws MessagingException;

    void sendAcceptReason(String response, String recipient) throws MessagingException;

}