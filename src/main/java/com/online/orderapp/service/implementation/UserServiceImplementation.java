package com.online.orderapp.service.implementation;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.dto.UserLoginResponseDto;
import com.online.orderapp.dto.UserResponseDto;
import com.online.orderapp.entity.User;
import com.online.orderapp.mapper.UserMapper;
import com.online.orderapp.repository.UserRepository;
import com.online.orderapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User saveUser(User user) {
		//encode raw password before saving
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}

	@Override
	public UserResponseDto getUser(Integer id) {
		User user = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User with id : "+id+" not found"));
		return userMapper.toUserResponse(user);
	}
	
	@Override
	public UserLoginResponseDto login(String userName, String rawPassword) {
		
		User user = userRepository.findByUserNameWithCart(userName).orElseThrow(()-> new NoSuchElementException("Incorrect Login Credentials"));
		
		// Check password using BCrypt
		if(!passwordEncoder.matches(rawPassword, user.getPassword())) {
			throw new NoSuchElementException("Invalid username or password");
		}
		
		return userMapper.toLoginResponse(user);
	}
	
	@Cacheable(value="user_cache")
	@Override
	public Page<User> getAllUsers(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> page = userRepository.findAll(pageable);
		return page;
		
	}


	@Override
	@CachePut(value="user_cache")
	public User updateUser(User user, Integer id) {
		User fetchedUser = userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User not found"));
		if(fetchedUser!=null) {
			fetchedUser.setUserName(user.getUserName());
			fetchedUser.setAddress(user.getAddress());
			fetchedUser.setContactNumber(user.getContactNumber());
			fetchedUser.setEmail(user.getEmail());
			fetchedUser.setGender(user.getGender());
			fetchedUser.setImage(user.getImage());
			fetchedUser.setOrders(user.getOrders());
			fetchedUser.setPassword(user.getPassword());
			userRepository.save(fetchedUser);
		}
		return fetchedUser;
	}

	@Override
	@CacheEvict(value="user_cache")
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
		userRepository.delete(user);
	}
	
	@CacheEvict(value="user_cache", allEntries = true)
	@Scheduled(fixedRate = 120000)
	public void evictAllCache() {
		System.out.println("Evicting all entries from 'user_cache' cache");
	}

	@Override
	public String uploadImage(MultipartFile file, Integer id) throws IOException {

		byte[] image = file.getBytes();
		User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
		user.setImage(image);
		userRepository.save(user);
		return "Image Uploaded";
	}

	@Override
	public byte[] getImage(Integer id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("User not found"));
		byte[] image = user.getImage();
		if(image==null || image.length==0) {
			throw new NoSuchElementException("User with id :"+id+" does not have any image");
			
		}
		return image;
	}

	@Override
	public String deleteAllUser() {
		userRepository.deleteAll();
		return "User Deleted";
	}





}
