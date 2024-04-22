package com.clinic.controllers.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import com.clinic.data.models.User;
import com.clinic.data.services.UserService;
import com.clinic.models.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.clinic.security.JWT;

@RestController
public class AuthController{

	@Autowired 
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JWT jwt;

	
	@PostMapping(value="/api/users/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login (@Valid @RequestBody Login loginData, Errors errors){

		if (loginData != null) {

			if (errors.hasErrors()) {
				Map<String, String> errorsMap = new HashMap<>();

				for (FieldError fe : errors.getFieldErrors()) {
					errorsMap.put(fe.getField(), fe.getDefaultMessage());
				}

				return new ResponseEntity<Map<?, ?>>(errorsMap, HttpStatus.BAD_REQUEST);
			}
			
		} else {
			Map<String, String> errorsMap = new HashMap<>();
			errorsMap.put("error", "no request body found");
			return new ResponseEntity<Map<?, ?>>(errorsMap, HttpStatus.BAD_REQUEST);
		}
	    
		String username = loginData.getUsername();
		String password = loginData.getPassword();
		
		try {
			User user = userService.findByUsername(username).get(0);

			
			if (passwordEncoder.matches(password, user.getPassword())) {
				
				SecurityContext securityContext = SecurityContextHolder.getContext();

				UsernamePasswordAuthenticationToken springauthToken = new UsernamePasswordAuthenticationToken(username,
																											  securityContext);				
				securityContext.setAuthentication(springauthToken);

				Map<String, String> responsMap = new HashMap<>();

				String token = jwt.generateToken(springauthToken);
				
				responsMap.put("token", token);
			
				responsMap.put("expirationDate",   String.valueOf(jwt.getClaims(token).getExpiration().getTime()));
				
				return ResponseEntity.ok(responsMap);
		    
			} else{
				Map<String, String> responseMap = new HashMap<>();
				responseMap.put("error", "username or password is wrong!");
				return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NOT_FOUND);
			}

	    
		}

		catch (NotFoundException e) {
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("error", "username or password is wrong!");
			return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.NOT_FOUND);
		
		}
		
		// return accessToken;
		
	}
}
