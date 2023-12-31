package com.shopfam.admin.security;

import java.util.Collection;
import java.util.List;
import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.*;

import com.shopfam.common.entity.*;

public class ShopfamUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	
	public ShopfamUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
		return user.isEnabled();
	}
	public String getFullname() {
		return this.user.getFirstname() + " " + this.user.getLastname();
	}
	public void setFirstName(String firstname) {
		this.user.setFirstname(firstname);
	}
	public void setLastName(String lastname) {
		this.user.setLastname(lastname);
	}
	
	public boolean hasRole(String roleName) {
		return user.hasRole(roleName);
	}

}
