package rs.ac.uns.ftn.isa.fisherman.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.mail.AccountAcceptedInfo;
import rs.ac.uns.ftn.isa.fisherman.mail.AccountDeniedInfo;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.UserRepository;
import rs.ac.uns.ftn.isa.fisherman.service.AuthorityService;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;
import rs.ac.uns.ftn.isa.fisherman.mail.MailService;
import javax.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MailService<String> mailService;
    private  AuthorityService authorityService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, MailService<String> mailService,AuthorityService authorityService,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.mailService=mailService;
        this.authorityService=authorityService;
        this.passwordEncoder =passwordEncoder;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) throws UsernameNotFoundException {
      return userRepository.findByEmail(email);
    }
    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }

    @Override
    public CabinOwner registerCabinOwner(CabinOwner cabinOwner, String sourceURL) throws MessagingException {
        List<Authority> auth = authorityService.findByname(cabinOwner.getRoleApp());
        cabinOwner.setAuthorities(auth);
        cabinOwner.setPassword(passwordEncoder.encode(cabinOwner.getPassword()));
        cabinOwner=userRepository.save(cabinOwner);
        return cabinOwner;
    }
    @Override
    public BoatOwner registerBoatOwner(BoatOwner boatOwner, String sourceURL) throws MessagingException {
        List<Authority> auth = authorityService.findByname(boatOwner.getRoleApp());
        boatOwner.setAuthorities(auth);
        boatOwner.setPassword(passwordEncoder.encode(boatOwner.getPassword()));
        boatOwner=userRepository.save(boatOwner);
        return boatOwner;
    }

    @Override
    public FishingInstructor registerFishingInstructor(FishingInstructor fishingInstructor, String sourceURL) throws MessagingException {
        List<Authority> auth = authorityService.findByname(fishingInstructor.getRoleApp());
        fishingInstructor.setAuthorities(auth);
        fishingInstructor.setPassword(passwordEncoder.encode(fishingInstructor.getPassword()));
        fishingInstructor=userRepository.save(fishingInstructor);
        return fishingInstructor;
    }
    public User activateAccount(String email, String code) {
        User user = findByEmail(email);
        if (!user.getActivationURL().equals(code)) {
            return null;
        }
        user.setEnabled(true);
        user.setActivationURL(null);
        user = this.userRepository.save(user);
        return user;
    }
    private void sendActivationURL(CabinOwner cabinOwner, String sourceURL) throws MessagingException {
        String verificationURL= sourceURL + "/activation/" + cabinOwner.getActivationURL() + "/" + cabinOwner.getEmail();
        mailService.sendMail(cabinOwner.getEmail(),verificationURL,new AccountAcceptedInfo());
    }
    public void acceptAccount(User user){
        user.setEnabled(true);
        userRepository.save(user);
        try {
            mailService.sendMail(user.getEmail(),user.getEmail(),new AccountAcceptedInfo());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void denyAccount(User user,String reason){
        String email=user.getEmail();
        userRepository.delete(user);

        try {
            mailService.sendMail(email,reason,new AccountDeniedInfo());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editUser(UserRequestDTO userRequest) {
        User newInfo= findByEmail(userRequest.getEmail());
        newInfo.setPhoneNum(userRequest.getPhoneNum());
        newInfo.getAddress().setCountry(userRequest.getAddress().getCountry());
        newInfo.getAddress().setCity(userRequest.getAddress().getCity());
        newInfo.getAddress().setStreetAndNum(userRequest.getAddress().getStreetAndNum());
        newInfo.getAddress().setLongitude(userRequest.getAddress().getLongitude());
        newInfo.getAddress().setLatitude(userRequest.getAddress().getLatitude());
        userRepository.save(newInfo);
    }

}