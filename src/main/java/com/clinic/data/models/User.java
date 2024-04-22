package com.clinic.data.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.clinic.data.models.UserPermission;


@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@Column
	@NotNull(message = "this field can't be null")
	private String username;

	@Column
	@NotNull(message = "this field can't be null")
	private String password;
	
	@Column
	private String email;

	@Null
	@OneToMany(mappedBy = "user")
    private Collection<UserPermission> userPermissions;


	
	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Collection<UserPermission> getUserPermissions() {
		return userPermissions;
	}



	public void setUserPermissions(Collection<UserPermission> userPermissions) {
		this.userPermissions = userPermissions;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
}
