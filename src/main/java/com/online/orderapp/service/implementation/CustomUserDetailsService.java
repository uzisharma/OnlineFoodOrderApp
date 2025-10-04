package com.online.orderapp.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.online.orderapp.entity.User;
import com.online.orderapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUserName(username)
				.orElseThrow(()-> new UsernameNotFoundException(username + " is not available"));
		
		String roleName = "ROLE_" + user.getRole().name();
		GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUserName())
				.password(user.getPassword())
				.authorities(authority)
				.build();
	}

}
