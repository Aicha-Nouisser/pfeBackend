package tn.esprit.spring.service;
/*
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Module;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.repository.ModuleRepository;
import tn.esprit.spring.repository.RoleRepository;

@Service
public class Moduleimpl implements ModuleServicee{
	@Autowired
	ModuleRepository repository;
	@Autowired
	RoleRepository  ur;
	@Override
	public void addRoleToModule(Integer idRole, Integer idModule) {
		  System.out.println(idRole);
			Role u= ur.findById(idRole).get();
			System.out.println(u);
			Module mod= repository.findById(idModule).get();
			u.getModules().add(mod);
			ur.save(u);
		}


    // Other service methods for module and role management
}*/