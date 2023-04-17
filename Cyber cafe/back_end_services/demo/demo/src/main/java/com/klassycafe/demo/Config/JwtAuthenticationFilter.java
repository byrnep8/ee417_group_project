package com.klassycafe.demo.Config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.klassycafe.demo.Service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String userName;
		
		// Check if authorisation header is empty or doesn't contain Bearer token
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request,  response);;
			return;
		}
		
		// Bearer ends 7 positions into string
		jwtToken = authHeader.substring(7);
		// Extract user name from jwt token
		userName = jwtService.extractUserName(jwtToken);
		// Check username null or if already connected/authenticated if null user not connected
		if( userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// Check if user is in database
			try{
				UserDetails user = this.userDetailsService.loadUserByUsername(userName);
			
				// Check if token is valid
				if (jwtService.isTokenValid(jwtToken, user)) {
					// System.out.println("Token is valid");
					// Update Security context
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities() );
					
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
			catch (Exception e) {
				System.out.println("Error encountered in token validation");
			}
			
			// Passing filter
			filterChain.doFilter(request, response);
		}
	}
	
}
