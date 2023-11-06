package com.shopfam.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.shopfam.admin.user.UserRepository;
import com.shopfam.common.entity.User;

public class ShopfamUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepo.getUserByEmail(email);
		if (user != null) {
			return new ShopfamUserDetails(user);
		}
		throw new UsernameNotFoundException("Could not find user with email: " + email);
	}

}

