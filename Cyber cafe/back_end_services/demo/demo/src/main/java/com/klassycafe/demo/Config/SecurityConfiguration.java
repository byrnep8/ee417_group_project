package com.klassycafe.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.klassycafe.demo.Config.*;
import com.klassycafe.demo.Service.UserService;

import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authProvider;
	private final JwtAuthEntryPoint authEntryPoint;
	
	@Bean
    public CustomAccesDeniedHandler accessDeniedHandler() {
        return new CustomAccesDeniedHandler();
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler()).authenticationEntryPoint(authEntryPoint).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeHttpRequests(authorize ->
                authorize.requestMatchers(HttpMethod.POST, "/post/reservations").permitAll()
                .requestMatchers(HttpMethod.POST, "/authentication").permitAll()
                .requestMatchers(HttpMethod.POST, "/post/reservation_info").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/get/*").hasAuthority("ADMIN")
                .anyRequest().authenticated())
        .authenticationProvider(authProvider);
		
		return http.build();
	}
}
