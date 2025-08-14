package com.online.orderapp.service.implementation;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.entity.User;
import com.online.orderapp.repository.UserRepository;
import com.online.orderapp.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService{
	
	private final UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUser(Integer id) {
		return userRepository.findById(id).orElseThrow(()->new NoSuchElementException("User with id : "+id+" not found"));
	}
	
	@Cacheable(value="user_cache", key="#id")
	@Override
	public Page<User> getAllUsers(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<User> page = userRepository.findAll(pageable);
		return page;
		
	}


	@Override
	@CachePut(value="user_cache", key="#id")
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
	@CacheEvict(value="user_cache", key="#id")
	public void deleteUser(Integer id) {
		User user = getUser(id);
		userRepository.delete(user);
	}

	@Override
	public String uploadImage(MultipartFile file, Integer id) throws IOException {

		byte[] image = file.getBytes();
		User user = getUser(id);
		user.setImage(image);
		userRepository.save(user);
		return "Image Uploaded";
	}

	@Override
	public byte[] getImage(Integer id) {
		// TODO Auto-generated method stub
		User user = getUser(id);
		byte[] image = user.getImage();
		if(image==null || image.length==0) {
			throw new NoSuchElementException("User with id :"+id+" does not have any image");
			
		}
		return image;
	}



}
