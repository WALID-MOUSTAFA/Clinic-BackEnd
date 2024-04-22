package com.clinic.controllers.admin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.clinic.data.models.User;
import com.clinic.data.models.UserPermission;
import com.clinic.data.services.UserService;
import com.clinic.models.Login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController{

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	
	@GetMapping("/admin/login/")
	public String GETLogin(Model model) {

		
		
		model.addAttribute("loginForm", new Login());
		model.addAttribute("title", "login");
		return "admin/login";
	}


	@PostMapping("/admin/login/")
	public String POSTLogin (@ModelAttribute("loginForm") @Validated Login login,
							 Errors error, Model model, HttpServletRequest request) {
				
		if (error.hasErrors()) {
			return "admin/login";
		}

		try {
			User user = userService.findByUsername(login.getUsername()).get(0);
			if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
				error.rejectValue("username", null,"username or password is wrong! try again");
				return "admin/login";
			}

			Collection<UserPermission> perms = user.getUserPermissions();
			List<UserPermission> l = (List<UserPermission>) perms;
			List<GrantedAuthority> grantedauthorities = new ArrayList<>();
			
			l.forEach(e ->{
					grantedauthorities.add(new SimpleGrantedAuthority(e.getPermission().getName()));
			});

			UsernamePasswordAuthenticationToken springAuthToken = new UsernamePasswordAuthenticationToken(login.getUsername(),null, grantedauthorities);
			springAuthToken.setDetails(new WebAuthenticationDetails(request));
			SecurityContext sctx = SecurityContextHolder.getContext();
			sctx.setAuthentication(springAuthToken);
			HttpSession session = request.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sctx);			
			return "redirect:/admin/home/";
		}
		catch (Throwable e) {
			error.rejectValue("username",null, "username dosn't exist");
			return "admin/login";
		}

	    
	}


	
}
