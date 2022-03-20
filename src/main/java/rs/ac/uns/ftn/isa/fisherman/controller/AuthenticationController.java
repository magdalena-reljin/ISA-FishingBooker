package rs.ac.uns.ftn.isa.fisherman.controller;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.ChangePasswordDto;
import rs.ac.uns.ftn.isa.fisherman.dto.LogInDto;
import rs.ac.uns.ftn.isa.fisherman.mapper.*;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.service.*;
import rs.ac.uns.ftn.isa.fisherman.service.impl.CustomUserDetailsService;


@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    private final String success = "Success.";
    private final String emailAlreadyInUse = "Email already in use.";

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    public  AuthenticationController(LoginService logInService){
      this.loginService = logInService;
  }

    private final CabinOwnerMapper cabinOwnerMapper = new CabinOwnerMapper();
    private final BoatOwnerMapper boatOwnerMapper = new BoatOwnerMapper();
    private final FishingInstructorMapper fishingInstructorMapper = new FishingInstructorMapper();
    private final ClientMapper clientMapper = new ClientMapper();
    private final UserMapper userMapper=new UserMapper();

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LogInDto userRequest) {
        try{
            UserTokenState userTokenState = loginService.LogIn(userRequest.getUsername(),userRequest.getPassword());
            return ResponseEntity.ok(userTokenState);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Incorrect credentials.");
        }
    }

    @PostMapping("/signUpCabinOwner")
    public ResponseEntity<String> registerCabinOwner(@RequestBody UserRequestDTO userRequest) throws Exception {

            User existUser=userService.findByUsername(userRequest.getUsername());
            if(existUser== null)
            {
                this.userService.registerCabinOwner(cabinOwnerMapper.userRequestDTOToCabinOwner(userRequest));
                return ResponseEntity.status(201).body(success);
            }
              return ResponseEntity.badRequest().body(emailAlreadyInUse);
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
    public ResponseEntity<String> registerBoatOwner(@RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>(emailAlreadyInUse, HttpStatus.BAD_REQUEST);
        }
        this.userService.registerBoatOwner(boatOwnerMapper.userRequestDtoToBoatOwner(userRequest));
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @PostMapping("/signUpFishingInstructor")
    public ResponseEntity<String> registerFishingInstructor( @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>(emailAlreadyInUse, HttpStatus.BAD_REQUEST);
        }
        this.userService.registerFishingInstructor(fishingInstructorMapper.userRequestDtoToFishingInstructor(userRequest));
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @PostMapping("/signUpClient")
    public ResponseEntity<String> registerClient( @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByUsername(userRequest.getUsername());
        if (existUser != null) {
            return new ResponseEntity<>(emailAlreadyInUse, HttpStatus.BAD_REQUEST);
        }
        this.userService.registerClient(clientMapper.userRequestDtoToClient(userRequest));
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePassword) {
        userDetailsService.changePassword(changePassword.getOldPassword(),changePassword.getNewPassword());
        return new ResponseEntity<>(success, HttpStatus.OK);
    }
    @PostMapping("/saveDeleteAccountRequest")
    public ResponseEntity<String> saveDeleteAccountRequest( @RequestBody UserRequestDTO userRequest) {
        userService.saveDeleteAccountRequest(userRequest.getUsername(),userRequest.getReasonForDeleting());
        return new ResponseEntity<>(success, HttpStatus.CREATED);
    }
}