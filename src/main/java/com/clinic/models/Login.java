package com.clinic.models;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;



public class Login{

	@NotNull(message = "username is required")
    @Length(min = 7)
	private String username;

	@NotNull(message = "password is required")
	@Length(min = 7)
	private String password;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
