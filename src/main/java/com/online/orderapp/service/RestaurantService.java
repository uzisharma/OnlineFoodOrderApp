package com.online.orderapp.service;


import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.dto.restaurantDto.RestaurantDetailResponseDto;
import com.online.orderapp.dto.restaurantDto.RestaurantRequestDto;
import com.online.orderapp.dto.restaurantDto.RestaurantResponseDto;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;

public interface RestaurantService {
	
	public RestaurantResponseDto createRestaurant(RestaurantRequestDto request);

	public RestaurantDetailResponseDto fetchById(int id);

//	public List<Restaurant> getAllRestaurant();

	//pagination method
	Page<RestaurantResponseDto> getAllRestaurants(int pageNum, int pageSize, String sortBy);

	public RestaurantResponseDto updateRestaurant(int id, RestaurantRequestDto request);

	public void deleteRestaurant(Integer id);

	Restaurant assignFood(Integer restaurantId, Set<Integer> foodId);
	
	List<Food> findFoodByRestaurantId(Integer id);

	public String uploadImage(MultipartFile image, Integer id) throws IOException;
	
}
