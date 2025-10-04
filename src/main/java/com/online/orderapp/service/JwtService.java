package com.online.orderapp.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.online.orderapp.util.Role;

public interface JwtService {

	public String generateToken(UserDetails userDetails);
	
	public String extractUsername(String token);
	
	public Role extractRole(String token);
	
	public boolean validateToken(String token);
	
	
}
