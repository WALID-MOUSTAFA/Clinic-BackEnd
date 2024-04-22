package com.clinic.security;

import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;


@Service
public class JWT{

	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secret;



	public String generateToken(Authentication authentication){    
		Date date = new Date();
		Date expirationDate = new Date(date.getTime() + this.expiration); 
	    return Jwts.builder()
			.setSubject(authentication.getName())
			.setIssuer("walidInc")
			.setExpiration(expirationDate)
			.setIssuedAt(date)
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}
	
	

	public Claims getClaims(String token){
		Claims claims = Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(token)
			.getBody();

		return claims;
	}



	public boolean validateToken(String token){
		

		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (JwtException e){
			System.out.println(e.getMessage());
			return false;
		}

		
	}
}
