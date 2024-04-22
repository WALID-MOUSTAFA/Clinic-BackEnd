package com.clinic.controllers;

import java.util.List;

import com.clinic.data.models.User;
import com.clinic.data.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Play{

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	// @RequestMapping("/play/moc_login")
	// public String listAll(@RequestParam("name") String name, @RequestParam("password") String password){
		
	// 	User user = userService.findByUsername(name).get(0);

	// 	if (passwordEncoder.matches(password,  user.getPassword())) {

	// 		UsernamePasswordAuthenticationToken springAuthToken = new UsernamePasswordAuthenticationToken(name,
	// 																									  passwordEncoder.encode(password));
	// 		SecurityContext securityCTX = SecurityContextHolder.getContext();
	// 		securityCTX.setAuthentication(springAuthToken);
	// 		return "Dsad";
			
	// 	}else{
	// 		throw new javax.ws.rs.NotFoundException("username or password are wrong");
	// 	}

		
	// }
	
	@RequestMapping("/play/insert")
	public String index(){
		String username, password, email;
		username = "walidsan";
		password = "walidsan";
		email = "walid@walid.com";

		User user = new User();

		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));

		userService.save(user);

		if (user.getId() != null) {
			return String.format("user %s inserted successfully", user.getId());
		}
		
		return "error";
	}
}
