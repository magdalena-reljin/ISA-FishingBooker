package rs.ac.uns.ftn.isa.fisherman.service;


import rs.ac.uns.ftn.isa.fisherman.model.CabinOwner;
import rs.ac.uns.ftn.isa.fisherman.model.User;

import javax.mail.MessagingException;
import java.util.List;


public interface UserService {
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll ();
    User registerCabinOwner(CabinOwner cabinOwner, String sourceURL) throws MessagingException;
}