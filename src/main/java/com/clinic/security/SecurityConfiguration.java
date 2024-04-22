package com.clinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {


	@Autowired
	JWTFilter jwtFilter;
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		
		http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout/"))
			.clearAuthentication(true)
			.logoutSuccessUrl("/admin/login/").deleteCookies("JSESSIONID", "SESSION")
			.and()
			.csrf().ignoringAntMatchers("/api/**", "/h2-console/**")
			.and()
			.antMatcher("/h2-console/**").headers().frameOptions().disable()
			.and()
			.antMatcher("/api/**")
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //token validation
		    .antMatcher("/**")
			.authorizeRequests().antMatchers("/api/users/login/**").permitAll()
			.antMatchers("/api/").authenticated()
			.antMatchers("/admin/home/**").hasAuthority("admin")
			.antMatchers("/h2-console/").permitAll()
			;
	    
		
	    
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	}

	@Bean
    public  PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}
