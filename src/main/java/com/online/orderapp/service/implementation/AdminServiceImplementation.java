package com.online.orderapp.service.implementation;
import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.online.orderapp.dto.adminDto.AdminLoginResponseDto;
import com.online.orderapp.dto.adminDto.AdminRequestDto;
import com.online.orderapp.dto.adminDto.AdminResponseDto;
import com.online.orderapp.entity.Admin;
import com.online.orderapp.mapper.AdminMapper;
import com.online.orderapp.repository.AdminRepository;
import com.online.orderapp.service.AdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImplementation implements AdminService{

    private final PasswordEncoder passwordEncoder;
	
	private final AdminRepository adminReposiotory;
	
	private final AdminMapper adminMapper;




	@Override
	public AdminResponseDto saveAdmin(AdminRequestDto request) {
		
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		request.setPassword(encodedPassword);
		
		Admin response = adminReposiotory.save(adminMapper.toEntity(request));
		
		return adminMapper.toDto(response);
	}
	
	
	@Override
	public AdminLoginResponseDto login(String userName, String rawPassword) {
	
		Admin admin = adminReposiotory.findByUserName(userName)
				.orElseThrow(()-> new NoSuchElementException("Invalid Username"));
		
		if(!passwordEncoder.matches(rawPassword, admin.getPassword())) {
			throw new NoSuchElementException("Invalid Password");
		}
	
		return adminMapper.toLoginResponse(admin);
	}




}
