package com.clinic.data.services;

import java.util.List;

import javax.ws.rs.NotFoundException;

import com.clinic.data.models.User;
import com.clinic.data.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService{

	@Autowired
	UserRepo repo;

	

	
	public void save(User user) {
		repo.save(user);
	}


	public List<User> findAll(){
		return repo.findAll();
	}

	public List<User> findByUsername(String username) {
		List <User> l =  repo.findByUsername(username);

		if (l.isEmpty()) {
			throw new NotFoundException();
		}

		return l;
	}


	
}
