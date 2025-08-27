package com.online.orderapp.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.dto.UserLoginResponseDto;
import com.online.orderapp.dto.UserResponseDto;
import com.online.orderapp.entity.User;

public interface UserService {

	User saveUser(User user);
	
	UserResponseDto getUser(Integer id);
	
	Page<User> getAllUsers(int pageNum, int pageSize);
	
	User updateUser(User user, Integer id);
	
	void deleteUser(Integer id);
	
	String uploadImage(MultipartFile file, Integer id) throws IOException;
	
	byte[] getImage(Integer id);

	UserLoginResponseDto login(String userName, String password);

	String deleteAllUser();

}
