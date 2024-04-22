package com.clinic.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;


@Service
class JWTFilter extends OncePerRequestFilter {


    @Autowired
	JWT jwt;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {

		
		String token = this.getJWTFromRequest(request);


		if (token != null && jwt.validateToken(token) ) {

			String username = jwt.getClaims(token).getSubject();

			SecurityContext securityContext = SecurityContextHolder.getContext();

			UsernamePasswordAuthenticationToken springAuthToken = new UsernamePasswordAuthenticationToken(username,
																										  null, null);			
			springAuthToken.setDetails(new WebAuthenticationDetails(request));

			securityContext.setAuthentication(springAuthToken);

		    
		}

		filterChain.doFilter(request, response);
		

	}

	private String getJWTFromRequest(HttpServletRequest request){
		String token = request.getHeader("Authorization");
		if (token != null && token.startsWith("Bearer ")) {
			
			return token.substring(7, token.length());
		}
		
		return null;
	}
	
}
