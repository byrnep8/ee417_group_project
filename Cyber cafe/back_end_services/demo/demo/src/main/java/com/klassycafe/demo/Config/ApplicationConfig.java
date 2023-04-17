package com.klassycafe.demo.Config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.klassycafe.demo.Entity.User;
import com.klassycafe.demo.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class ApplicationConfig {
	private static final Optional<User> ImmutableList = null;
	private final UserRepository repo;
	
	
	@Bean
	public UserDetailsService userDetialsService(){
		return username -> repo.findByUsername(username).orElseThrow( () -> new UsernameNotFoundException("User Not Located"));
	}
	
	// Fetch user details and encoding of details
	@Bean
	public AuthenticationProvider authenticationProvider() {
		// Data Access Object
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetialsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		 return new BCryptPasswordEncoder();
	}
}
