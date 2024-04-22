package com.clinic.security;

import java.util.Collection;

import com.clinic.data.models.UserPermission;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsModel implements UserDetails{

	private String  username;

	
	private UserPermission[] permissions;
	
	public UserDetailsModel(String username){
		this.username = username;
    
	}
	
	@Override
	public String getUsername() {
		
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public UserPermission[] getPermissions() {
		return permissions;
	}

	public void setPermissions(UserPermission[] permissions) {
		this.permissions = permissions;
	}

	
	
}
