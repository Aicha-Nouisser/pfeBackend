package tn.esprit.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.entity.*;
@Service


 public class UserImpl implements UserService{
	private final UserRepository repository;
    private final JavaMailSender javaMailSender;

    @Autowired
    public UserImpl(UserRepository repository, JavaMailSender javaMailSender) {
        this.repository = repository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void accepterUser(Long idUser) {
        User user = repository.findById(idUser).orElse(null);
        if (user != null) {
            user.setState(true);
            repository.save(user);
            sendConfirmationEmail(user);
        }
    }

    private void sendConfirmationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Confirmation de compte");
        message.setText("Cher " + user.getUsername() + ",\n\n"
                + "Votre compte a été accepté avec succès. Bienvenue sur notre plateforme.");

        javaMailSender.send(message);
    }

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
		
		
		
	}

	@Override
	public void deleteUserById(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return repository.saveAndFlush(user);
	}

	@Override
	public User updateUserById(User o, Long id) {
		User cl= repository.findById(id).orElse(null);
		
		


		cl.setUsername(o.getUsername());
		cl.setEmail(o.getEmail());
		cl.setState(o.isState());
		cl.setLastName(o.getLastName());
		cl.setPhoneNumber(o.getPhoneNumber());
		cl.setPostCode(o.getPostCode());
		cl.setAddress(o.getAddress());
		cl.setUserType(o.getUserType());
		cl.setCountry(o.getCountry());
		cl.setCity(o.getCity());
		cl.setClientType(o.getClientType());
		cl.setCompanyName(o.getCompanyName());
		cl.setPaymentEmail(o.getPaymentEmail());
		cl.setRoles(o.getRoles());
		cl.setPassword(o.getPassword());

		return repository.save(cl);
			}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
	    return repository.findById(id).orElse(null);

	}
}
