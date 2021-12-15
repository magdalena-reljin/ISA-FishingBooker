package rs.ac.uns.ftn.isa.fisherman.service;


import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequest;

import java.util.List;


public interface UserService {
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll ();
    User save(User user);
}