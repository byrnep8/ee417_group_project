package com.klassycafe.demo.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	// 256 bit secret key being defined (256 min for JWT)
	private static final String SECRET_KEY = "7134743777217A25432A462D4A614E645266556A586E3272357538782F413F44";
	
	// Getting the username from the token provided
	public String extractUserName(String jwtToken) {
		return extractClaim(jwtToken, Claims::getSubject);
	}
	
	public Key getSignInkey(){
		byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	// Generic method to extract claims
	public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
	}
	
	public String generateToken(UserDetails user) {
		return generateToken(new HashMap<>(), user);
	}
	
	// Generating token for user
	public String generateToken(
			Map<String, Object> extractClaims,
			UserDetails userDetails
			) {
		return Jwts.builder().setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24*7))
				.signWith(getSignInkey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	// Check if the token provided is valid
	public boolean isTokenValid(String token, UserDetails user) {
		// System.out.println("Validating Token");
		final String username = extractUserName(token);
		// System.out.println((username.equals(user.getUsername())) && !isTokenExpired(token));
		return (username.equals(user.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String jwtToken) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignInkey())
				.build()
				.parseClaimsJws(jwtToken)
				.getBody();
	}
}
