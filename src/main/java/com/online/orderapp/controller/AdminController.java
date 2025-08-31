package com.online.orderapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.dto.adminDto.AdminLoginRequestDto;
import com.online.orderapp.dto.adminDto.AdminLoginResponseDto;
import com.online.orderapp.dto.adminDto.AdminRequestDto;
import com.online.orderapp.dto.adminDto.AdminResponseDto;
import com.online.orderapp.service.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

	private final AdminService adminService;
	
	@PostMapping("/save")
	ResponseEntity<ResponseStructure<AdminResponseDto>> saveUser(@RequestBody AdminRequestDto request){
		AdminResponseDto response =adminService.saveAdmin(request);
		ResponseStructure<AdminResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Admin got created");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<AdminLoginResponseDto>> login(
			@RequestBody AdminLoginRequestDto loginRequest){
		
		AdminLoginResponseDto response = adminService.login(
				loginRequest.getUserName(), 
				loginRequest.getPassword()
				);
		ResponseStructure<AdminLoginResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Login Success");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
}
