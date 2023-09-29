package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.entity.User;


public interface UserService{
   public void accepterUser(Long idUser);
   public List<User> getUsers();
	public void deleteUserById(Long id);
	public User save(User user);
User updateUserById(User o,Long id);
	User findById(Long id);

}
