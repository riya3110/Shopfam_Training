package com.shopfam.security.oauth;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.shopfam.common.entity.AuthenticationType;
import com.shopfam.customer.CustomerService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.shopfam.common.entity.*;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired private CustomerService customerService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		CustomerOAuth2User oauth2user = (CustomerOAuth2User) authentication.getPrincipal();
		
		String name = oauth2user.getName();
		String email = oauth2user.getEmail();
		String countryCode = request.getLocale().getCountry();
		String clientName = oauth2user.getClientName();
		
		AuthenticationType authenticationType = getAuthenticationType(clientName);
		
		Customer customer = customerService.getCustomerByEmail(email);
		if(customer == null) {
			customerService.addNewCustomerUponOAuthLogin(name , email , countryCode);
		}else {
			oauth2user.setFullName(customer.getFullName());
			customerService.updateAuthenticationType(customer,authenticationType);
		}
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	 private AuthenticationType getAuthenticationType(String clientName) {
		if (clientName.equals("Google")) {
			return AuthenticationType.GOOGLE;
		} else if (clientName.equals("Facebook")) {
			return AuthenticationType.FACEBOOK;
		} else {
			return AuthenticationType.DATABASE;
		}
	}
	
}
