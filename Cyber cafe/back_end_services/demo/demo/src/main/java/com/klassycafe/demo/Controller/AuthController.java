package com.klassycafe.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klassycafe.demo.Service.AuthService;

import lombok.RequiredArgsConstructor;

// Authentication controller for the login processing
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("authentication")
public class AuthController {
	private final AuthService authService;
	
	@PostMapping()
	@CrossOrigin("*")
	public ResponseEntity<AuthResponse> auth(@RequestBody AuthRequest req){
		// System.out.println(req.getUsername());
		
		return ResponseEntity.ok(authService.authenticate(req));
	}
}
