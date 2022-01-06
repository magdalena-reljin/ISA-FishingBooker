package rs.ac.uns.ftn.isa.fisherman.controller;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isa.fisherman.dto.LogInDto;
import rs.ac.uns.ftn.isa.fisherman.dto.VerificationDTO;
import rs.ac.uns.ftn.isa.fisherman.mapper.BoatOwnerMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.CabinOwnerMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.FishingInstructorMapper;
import rs.ac.uns.ftn.isa.fisherman.mapper.UserMapper;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.dto.UserRequestDTO;
import rs.ac.uns.ftn.isa.fisherman.dto.UserTokenStateDTO;
import rs.ac.uns.ftn.isa.fisherman.security.TokenUtils;
import rs.ac.uns.ftn.isa.fisherman.service.*;
import rs.ac.uns.ftn.isa.fisherman.service.impl.CustomUserDetailsService;
import rs.ac.uns.ftn.isa.fisherman.service.impl.FirebaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
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
    FirebaseService firebaseService;

  @Autowired
  public  AuthenticationController(LoginService logInService){
      this.loginService = logInService;
  }

    private String success= "Success!";
    private CabinOwnerMapper cabinOwnerMapper = new CabinOwnerMapper();
    private BoatOwnerMapper boatOwnerMapper = new BoatOwnerMapper();
    private FishingInstructorMapper fishingInstructorMapper = new FishingInstructorMapper();

    private UserMapper userMapper=new UserMapper();

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(@RequestBody LogInDto userRequest) {
        UserTokenState userTokenState = loginService.LogIn(userRequest);
        return ResponseEntity.ok(userTokenState);
    }
    @PostMapping("/savePicture")
    public void savePicture(@RequestBody Photo photo) throws ExecutionException, InterruptedException {
        System.out.println("ovo je slikaaaaaaaa"+photo.getUrl());
        System.out.println("ovo je slikaaaaaaaa"+photo.getTitle());
        System.out.println("ovo je slikaaaaaaaa"+photo.getUrl());
        Photo newPhoto=new Photo(photo.getUrl(), photo.getTitle());
        firebaseService.savePhoto(newPhoto);
    }

    @PostMapping(value = "/refresh")
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
    }

    @PostMapping("/signUpCabinOwner")
    public ResponseEntity<String> registerCabinOwner(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        this.userService.registerCabinOwner(cabinOwnerMapper.userRequestDTOToCabinOwner(userRequest),httpServletRequest.getHeader("origin"));
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }
    @PostMapping("/acceptAccount")
    public ResponseEntity<String> acceptAccount(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        userService.acceptAccount(userService.findByEmail(userRequest.getEmail()));

        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
    @PostMapping("/denyAccount/{reason}")
    public ResponseEntity<String> denyAccount(@PathVariable ("reason") String reason, HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        userService.denyAccount(userService.findByEmail(userRequest.getEmail()),reason);
        return new ResponseEntity<>("Success.", HttpStatus.OK);
    }
    @GetMapping("/getUserByEmail/{email}")
    public UserRequestDTO getUserByEmail(@PathVariable ("email") String email){
        System.out.println("aaaaaaaaaaaaaaaaaaa ");
        return userMapper.userToUserRequestDTO(userService.findByEmail(email));
    }

    @PostMapping("/findByEmail")
    public UserRequestDTO findByEmail(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest){
        System.out.println("aaaaaaaaaaaaaaaaaaa ");
        return userMapper.userToUserRequestDTO(userService.findByEmail(userRequest.getEmail()));
    }

    @PostMapping("/signUpBoatOwner")
    public ResponseEntity<String> registerBoatOwner(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        this.userService.registerBoatOwner(boatOwnerMapper.userRequestDtoToBoatOwner(userRequest),httpServletRequest.getHeader("origin"));
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @PostMapping("/signUpFishingInstructor")
    public ResponseEntity<String> registerFishingInstructor(HttpServletRequest httpServletRequest, @RequestBody UserRequestDTO userRequest) throws MessagingException {
        User existUser = this.userService.findByEmail(userRequest.getEmail());
        if (existUser != null) {
            return new ResponseEntity<>("Email already in use.", HttpStatus.BAD_REQUEST);
        }
        this.userService.registerFishingInstructor(fishingInstructorMapper.userRequestDtoToFishingInstructor(userRequest),httpServletRequest.getHeader("origin"));
        return new ResponseEntity<>("Success.", HttpStatus.CREATED);
    }

    @GetMapping("/getNewUsers")
    public List<UserRequestDTO> getNewUsers() throws MessagingException {
        List<UserRequestDTO> newUsers=new ArrayList<UserRequestDTO>();
        for(CabinOwner cabinOwner: cabinOwnerService.getNewCabinOwners()) {
            newUsers.add(cabinOwnerMapper.CabinOwnerToUserRequestDto(cabinOwner));
        }
        for(BoatOwner boatOwner: boatOwnerService.getNewBoatOwners()) {
            newUsers.add(boatOwnerMapper.boatOwnerToUserRequestDto(boatOwner));
        }
        for(FishingInstructor fishingInstructor: fishingInstructorService.getNewFishingInstructors()) {
            newUsers.add(fishingInstructorMapper.fishingInstructorToUserRequestDto(fishingInstructor));
        }
        return newUsers;
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activate(@RequestBody VerificationDTO verificationDTO) {
        String email = verificationDTO.getEmail();
        String code = verificationDTO.getActivationCode();

        if(this.userService.activateAccount(email, code) != null){
            return new ResponseEntity<>(success, HttpStatus.OK);
        }
        return new ResponseEntity<>(success, HttpStatus.BAD_REQUEST);
    }


}