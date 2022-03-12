package rs.ac.uns.ftn.isa.fisherman.controller;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.ChangePasswordDto;
import rs.ac.uns.ftn.isa.fisherman.dto.LogInDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.*;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.security.TokenUtils;
import rs.ac.uns.ftn.isa.fisherman.service.*;
import rs.ac.uns.ftn.isa.fisherman.service.impl.CustomUserDetailsService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private CabinOwnerService cabinOwnerService;

    @Autowired
    private FishingInstructorService fishingInstructorService;

    @Autowired
    private BoatOwnerService boatOwnerService;

    private LoginService loginService;


    @Autowired
   public  AuthenticationController(LoginService logInService){
      this.loginService = logInService;
  }


    private CabinOwnerMapper cabinOwnerMapper = new CabinOwnerMapper();
    private BoatOwnerMapper boatOwnerMapper = new BoatOwnerMapper();
    private FishingInstructorMapper fishingInstructorMapper = new FishingInstructorMapper();
    private ClientMapper clientMapper = new ClientMapper();

    private UserMapper userMapper=new UserMapper();

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LogInDto userRequest) {
        try{
            UserTokenState userTokenState = loginService.LogIn(userRequest);
            return ResponseEntity.ok(userTokenState);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Password or username is incorrect");
        }
    }



   /* @PostMapping(value = "/refresh")
    public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken, expiresIn));
        } else {
            UserTokenStateDTO userTokenState = new UserTokenStateDTO();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }*/


    @PostMapping("/signUpCabinOwner")
    public ResponseEntity<String> registerCabinOwner(@RequestBody UserRequestDTO userRequest) throws Exception {

            User existUser=userService.findByUsername(userRequest.getUsername());
            if(existUser== null)
            {
                this.userService.registerCabinOwner(cabinOwnerMapper.userRequestDTOToCabinOwner(userRequest));
                return ResponseEntity.status(201).body("Success");
            }
              return ResponseEntity.badRequest().body("Email already in use.");
    }


    @PostMapping("/findByEmail")
    public UserRequestDTO findByEmail(@RequestBody UserRequestDTO userRequest){
        return userMapper.userToUserRequestDTO(userService.findByUsername(userRequest.getUsername()));
    }
    @PostMapping("/editUser")
    public void editUser(@RequestBody UserRequestDTO userRequest){
        userService.editUser(userRequest);
    }

    @PostMapping("/signUpBoatOwner")
    public ResponseEntity<String> registerBoatOwner(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        this.userService.registerBoatOwner(boatOwnerMapper.userRequestDtoToBoatOwner(userRequest));
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @PostMapping("/signUpFishingInstructor")
    public ResponseEntity<String> registerFishingInstructor( @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        this.userService.registerFishingInstructor(fishingInstructorMapper.userRequestDtoToFishingInstructor(userRequest));
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @PostMapping("/signUpClient")
    public ResponseEntity<String> registerClient( @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        this.userService.registerClient(clientMapper.userRequestDtoToClient(userRequest));
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePassword) {
        userDetailsService.changePassword(changePassword.getOldPassword(),changePassword.getNewPassword());
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
    @PostMapping("/saveDeleteAccountRequest")
    public ResponseEntity<String> saveDeleteAccountRequest( @RequestBody UserRequestDTO userRequest) {
        userService.saveDeleteAccountRequest(userRequest.getUsername(),userRequest.getReasonForDeleting());
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }
}