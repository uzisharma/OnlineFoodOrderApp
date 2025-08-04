package com.online.orderapp.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.online.orderapp.entity.Restaurant;

public interface RestaurantService {
	
	public Restaurant createRestaurant(Restaurant restaurant);

	public Restaurant fetchById(int id);

	public List<Restaurant> getAllRestaurant();

	//pagination method
	Page<?> getAllRestaurants(int pageNum, int pageSize, String sortBy);

	public Restaurant updateRestaurant(int id, Restaurant restaurant);

	public void deleteRestaurant(Integer id);
}
