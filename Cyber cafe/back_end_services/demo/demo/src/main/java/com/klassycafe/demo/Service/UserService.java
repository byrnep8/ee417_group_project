package com.klassycafe.demo.Service;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.klassycafe.demo.Entity.RoleEntity;
import com.klassycafe.demo.Entity.User;
import com.klassycafe.demo.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	public String getRole(String username) {
		List<User> users = userRepository.findAll();
		
		for ( User user : users ) {
			
			// comparing username against username inputted
			if( user.getUsername().equalsIgnoreCase(username) ) {
				
				// Return the role
				return user.getRole().toString();
			}
		}
		
		return "Not found";
	}
	
	// Granting authority to the roles
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleEntity> roles){
		List<GrantedAuthority> auth_vect = new Vector();
		
		for ( RoleEntity role : roles ) {
			auth_vect.add( new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
		}
		
		return auth_vect;
	}
}
