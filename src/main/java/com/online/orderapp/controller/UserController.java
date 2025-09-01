package com.online.orderapp.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.dto.ResponseStructure;
import com.online.orderapp.dto.userDto.UserLoginRequestDto;
import com.online.orderapp.dto.userDto.UserLoginResponseDto;
import com.online.orderapp.dto.userDto.UserRequestDto;
import com.online.orderapp.dto.userDto.UserResponseDto;
import com.online.orderapp.entity.User;
import com.online.orderapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	ResponseEntity<ResponseStructure<User>> saveUser(@Valid @RequestBody User user){
		User response =userService.saveUser(user);
		ResponseStructure<User> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("User got created");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<UserResponseDto>> getUserById(@PathVariable Integer id){
		UserResponseDto response = userService.getUser(id);
		ResponseStructure<UserResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("User with Id : "+ id+" founded successfully");
		apiResponse.setStatusCode(HttpStatus.FOUND.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<ResponseStructure<Page<User>>> getAllUser(
			@RequestParam(defaultValue = "0", required = false) int pageNum,
			@RequestParam(defaultValue="10", required= false) int PageSize
			){
		Page<User> response = userService.getAllUsers(pageNum, PageSize);
		ResponseStructure<Page<User>> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Data Fetched Acoording to page");
		apiResponse.setStatusCode(HttpStatus.OK.value());		
		return ResponseEntity.ok(apiResponse);				
	}
	
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<UserResponseDto>> updateUser(@RequestParam int id, @RequestBody UserRequestDto request){
		UserResponseDto response = userService.updateUser(request, id);
		ResponseStructure<UserResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("User updated successfully");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(apiResponse);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<User> deleteUser(@PathVariable Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/delete-all")
	public ResponseEntity<ResponseStructure<String>> deleteAllUser(){
		String response = userService.deleteAllUser();
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("All Users Deleted");
		apiResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<ResponseStructure<String>>(apiResponse, HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("/{id}/user/uploadImage")
	public ResponseEntity<ResponseStructure<String>> uploadImage(
			@RequestParam MultipartFile image, 
			@PathVariable Integer id) throws IOException{
		String response = userService.uploadImage(image, id);
		ResponseStructure<String> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Success");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/{id}/user/getImage", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id){
		byte[] image = userService.getImage(id);
//		ResponseStructure<byte[]> apiResponse = new ResponseStructure<>();
//		apiResponse.setData(image);
//		apiResponse.setMessage("Success");
//		apiResponse.setStatusCode(HttpStatus.OK.value());
		
//		return ResponseEntity.ok()
//				.contentType(MediaType.IMAGE_JPEG)
//				.contentType(MediaType.IMAGE_PNG)
//				.body(apiResponse);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(image);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<UserLoginResponseDto>> login(
			@RequestBody UserLoginRequestDto loginRequest){
		
		UserLoginResponseDto response = userService.login(
				loginRequest.getUserName(), 
				loginRequest.getPassword()
				);
		ResponseStructure<UserLoginResponseDto> apiResponse = new ResponseStructure<>();
		apiResponse.setData(response);
		apiResponse.setMessage("Login Success");
		apiResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
}
