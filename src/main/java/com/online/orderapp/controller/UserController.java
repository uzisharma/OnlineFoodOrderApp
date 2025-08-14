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
	public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable Integer id){
		User response = userService.getUser(id);
		ResponseStructure<User> apiResponse = new ResponseStructure<>();
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
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam int id, @RequestBody User user){
		User response = userService.updateUser(user, id);
		ResponseStructure<User> apiResponse = new ResponseStructure<>();
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
	
	@PatchMapping("/{id}/user/uploadImage")
	public ResponseEntity<ResponseStructure<String>> uploadImage(@RequestParam MultipartFile image, @PathVariable Integer id) throws IOException{
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
	
	
	
}
