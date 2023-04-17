package com.klassycafe.demo.Controller;

import com.klassycafe.demo.Entity.RoleName;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

	private String token;
	@Enumerated(EnumType.STRING)
	private RoleName role;
}
