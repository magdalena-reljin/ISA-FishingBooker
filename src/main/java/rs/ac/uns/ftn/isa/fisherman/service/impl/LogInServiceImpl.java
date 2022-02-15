package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.LogInDto;
import rs.ac.uns.ftn.isa.fisherman.model.User;
import rs.ac.uns.ftn.isa.fisherman.model.UserTokenState;
import rs.ac.uns.ftn.isa.fisherman.repository.UserRepository;

import rs.ac.uns.ftn.isa.fisherman.security.TokenUtils;
import rs.ac.uns.ftn.isa.fisherman.service.LoginService;


@Service
public class LogInServiceImpl implements LoginService {

    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public LogInServiceImpl(TokenUtils tokenUtils, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;

    }
    public UserTokenState LogIn(LogInDto authenticationRequest) {
        System.out.println("STIGLO OD DTTTOOO"+authenticationRequest.getUsername()+authenticationRequest.getPassword());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
        if(authentication.isAuthenticated()){
            System.out.println("AUTENTIFIKOVAN SA<<<<<MMMM");
        }else {
            System.out.println("NISAMMM JBGGGG!!!");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String userType= userRepository.findRoleById(user.getId());
        String accessToken = tokenUtils.generateToken(user.getUsername());
        int accessExpiresIn = tokenUtils.getExpiredIn();
        System.out.println("USERRRR"+userType);
        return new UserTokenState(userType,accessToken, accessExpiresIn);

    }


}
