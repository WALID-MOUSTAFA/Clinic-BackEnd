package com.clinic.security;

import com.clinic.data.models.User;
import com.clinic.data.models.UserPermission;
import com.clinic.data.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetails_Service implements UserDetailsService{


	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		User user = userService.findByUsername(username).get(0);
		return new UserDetailsModel(username);
	}

	
}
