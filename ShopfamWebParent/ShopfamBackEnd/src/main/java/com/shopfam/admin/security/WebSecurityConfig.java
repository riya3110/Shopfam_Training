package com.shopfam.admin.security;

import org.springframework.context.annotation.Bean;



import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.core.userdetails.*;



@Configuration
@EnableWebSecurity()
public class WebSecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new ShopfamUserDetailsService();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
		AuthenticationConfiguration authConfig) throws Exception {
			return authConfig.getAuthenticationManager();
	}
	
	
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .requestMatchers("/users/**", "/settings/**" , "/countries/**" , "/states/**").hasAuthority("Admin")
                .requestMatchers("/users/**").hasAuthority("Admin")
                .requestMatchers("/categories/**" , "/brands/**").hasAnyAuthority("Admin" , "Editor")
                                
                .requestMatchers("/products/**").hasAnyAuthority("Admin" , "Editor" , "Salesperson" , "Shipper")
                
                .requestMatchers("/products/edit/**" , "/products/save" , "/products/check_unique")
                   .hasAnyAuthority("Admin" , "Editor" , "Salesperson")

                .requestMatchers("/products" , "/products/" , "/products/detail/**" , "/products/page/**")
                	.hasAnyAuthority("Admin" , "Editor" , "Salesperson" , "Shipper")
                	
                .requestMatchers("/products/**").hasAnyAuthority("Admin" , "Editor")
                
                .requestMatchers("/customers/**" , "/orders/**" , "/get_shipping_cost").hasAnyAuthority("Admin" , "Salesperson")
                
                
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("email")
                        .permitAll())
                .logout(logout -> logout
                        .permitAll()).rememberMe(me -> me.key("Abcdefghijklmnopqr_1234567890").tokenValiditySeconds(7*24*60*60));
        http.headers(headers -> headers.frameOptions().sameOrigin());
        
        return http.build();  
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	return (web) -> web.ignoring().requestMatchers("/Images/**" ,"/js/**" ,  "/webjars/**");
    }

}

