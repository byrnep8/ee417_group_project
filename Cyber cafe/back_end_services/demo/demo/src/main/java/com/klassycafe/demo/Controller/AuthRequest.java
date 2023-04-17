package com.klassycafe.demo.Controller;

import com.klassycafe.demo.Entity.RoleName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

	private String username;
	String password;
	
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}

}