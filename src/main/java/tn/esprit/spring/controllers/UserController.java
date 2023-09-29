package tn.esprit.spring.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Site;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {

	 @Autowired
	  UserRepository userRepository;
	  @Autowired 
		UserService cs;
	
	  @Transactional

	  @DeleteMapping("/deleteUser/{id}")
		@ResponseBody
		public void deleteUsers(@PathVariable ("id") Long id) {
cs.deleteUserById(id);	}
	
	  @PostMapping("/addUser")
		@ResponseBody
		public User  addUsers(@RequestBody User user) {
		    return  cs.save(user);
		}
	  @PutMapping("/modify-User/{id}")
		@ResponseBody
		public User ModifyUser(@PathVariable("id") Long id,@RequestBody User t) {
			return cs.updateUserById(t, id);

			}
	
	  @GetMapping("/getUserby/{id}")
		@ResponseBody
		public User getUsersbyid(@PathVariable("id") Long id) {
		    return cs.findById(id);
		}
}
