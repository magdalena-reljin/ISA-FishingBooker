package rs.ac.uns.ftn.isa.fisherman.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequest;
import rs.ac.uns.ftn.isa.fisherman.repository.UserRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AuthorityService;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityService authService;

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String username) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(username);
        return u;
    }


    public List<User> findAll() throws AccessDeniedException {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }


}