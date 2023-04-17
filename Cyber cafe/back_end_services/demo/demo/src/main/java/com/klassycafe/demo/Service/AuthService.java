package com.klassycafe.demo.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.klassycafe.demo.Controller.AuthRequest;
import com.klassycafe.demo.Controller.AuthResponse;
import com.klassycafe.demo.Repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwdEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;

	
	public AuthResponse authenticate(AuthRequest req) {
		// TODO Auto-generated method stub
		// System.out.println(req.getPassword());
		authManager.authenticate(new UsernamePasswordAuthenticationToken(
				req.getUsername(), req.getPassword()));
		// Generating token to send back
		var user = userRepo.findByUsername(req.getUsername()).orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		
		return AuthResponse.builder().token(jwtToken).role(user.getRole()).build();
	}
	
}
