package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.payload.request.LoginRequest;
import tn.esprit.spring.payload.request.SignupRequest;
import tn.esprit.spring.payload.response.JwtResponse;
import tn.esprit.spring.payload.response.MessageResponse;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.security.jwt.JwtUtils;
import org.springframework.mail.SimpleMailMessage;
import tn.esprit.spring.entity.*;
import tn.esprit.spring.security.services.UserDetailsImpl;
import tn.esprit.spring.repository.RoleRepository;
import tn.esprit.spring.service.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;
  //@Autowired 
  //TransactionService ss;
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  @Autowired 
	UserService cs;
  
  @Autowired
  private JavaMailSender javaMailSender;
 /// @Autowired
  ///ModuleServicee bs;

  //@Transactional
 /// @PostMapping("/addRoleToModule/{idRole}/{idModule}")
  //@ResponseBody
  ///public void addRoleToModule(@PathVariable("idRole") Integer idRole, @PathVariable("idModule") Integer idModule) {
     /// bs.addRoleToModule(idRole, idModule);
  ///}


  
/*
@PutMapping("/addTransactiontoSite/{idTransaction}/{idSite}")
@ResponseBody
public void AffecterTransactionSite(@PathVariable ("idTransaction") Long idTransaction ,@PathVariable("idSite") Long idSite)
{
 ss.AffecterTransactionSite(idTransaction, idSite);
}*/
  
  @PostMapping("/accepterUser/{idUser}")
 	@ResponseBody
 	public void accepterUser(@PathVariable("idUser") Long idUser )
 	{
 		cs.accepterUser(idUser);
 		
 	}
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	   
	    Optional<User> user= userRepository.findByUsername(loginRequest.getUsername());
	    if (!user.get().isState()) {
	    	throw new RuntimeException("user non actif");}
	    
	        Authentication authentication = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String jwt = jwtUtils.generateJwtToken(authentication);

	        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	        List<String> roles = userDetails.getAuthorities().stream()
	            .map(item -> item.getAuthority())
	            .collect(Collectors.toList());


          return ResponseEntity.ok(new JwtResponse(jwt,
                               userDetails.getId(),
                               userDetails.getUsername(),
                               userDetails.getEmail(),
                               roles));
      
  }
  @PostMapping(value = "/signup", consumes = {"multipart/form-data"})
  public ResponseEntity<?> registerUser(@Valid @ModelAttribute SignupRequest signUpRequest) {
      if (userRepository.existsByUsername(signUpRequest.getUsername())) {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Username is already taken!"));
      }

      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
          return ResponseEntity
                  .badRequest()
                  .body(new MessageResponse("Error: Email is already in use!"));
      }

      //valider champs obligatoire :
      signUpRequest.validateRequest();
      // Create new user's account
      User user = initUser(signUpRequest);

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
      } else {
          strRoles.forEach(role -> {
              switch (role) {
                  case "admin":
                      Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                      roles.add(adminRole);

                      break;
                  case "mar":
                      Role modRole = roleRepository.findByName(ERole.ROLE_MARCHAND)
                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                      roles.add(modRole);

                      break;
                  default:
                      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                      roles.add(userRole);
              }
          });
      }

      user.setRoles(roles);
      userRepository.save(user);

      return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  private User initUser(SignupRequest signUpRequest) {

      User user = new User();
      user.setUserType(signUpRequest.getUserType());
      user.setUsername(signUpRequest.getUsername());
      user.setEmail(signUpRequest.getEmail());
      user.setPassword(encoder.encode(signUpRequest.getPassword()));
     // user.setFirstName(signUpRequest.getFirstName());
      user.setLastName(signUpRequest.getLastName());
      user.setPhoneNumber(signUpRequest.getPhoneNumber());
      user.setCity(signUpRequest.getCity());
      user.setAddress(signUpRequest.getAddress());
      user.setPostCode(signUpRequest.getPostCode());
      //init Marchand information
      if (UserType.MARCHAND.equals(signUpRequest.getUserType())) {
          user.setClientType(signUpRequest.getClientType());
          user.setCompanyName(signUpRequest.getCompanyName());
          user.setPaymentEmail(signUpRequest.getPaymentEmail());
          Site site = new Site();
          site.setShopName(signUpRequest.getShopName());
          site.setUrl(signUpRequest.getUrl());
          site.setTechnicianName(signUpRequest.getTechnicianName());
          site.setDescription(signUpRequest.getDescription());
          site.setCategory(signUpRequest.getCategory());
          try {
              site.setProfilePic(signUpRequest.getProfilePic().getBytes());
          } catch (IOException e) {
              System.out.println(e.getMessage());
          }
          site.setEmailSite(signUpRequest.getEmailSite());
          site.setPhoneNumberSite(signUpRequest.getPhoneNumberSite());
          site.setNameTechnician(signUpRequest.getNameTechnician());
          site.setCompleteAddress(signUpRequest.getCompleteAddress());
          site.setActivitySector(signUpRequest.getActivitySector());
          site.setRevenue(signUpRequest.getRevenue());
          user.setSite(site);
      }
      return user;
  }
  
  @Transactional
	@GetMapping("/User")
	@ResponseBody
	public List<User> getUsers(){
		return cs.getUsers();
	}
  /*@Transactional

  @DeleteMapping("/deleteUser/{id}")
	@ResponseBody
	public void deleteUsers(@PathVariable ("idUser") Long idUser) {
cs.deleteUserById(idUser);
}
  */
  
  
  
}