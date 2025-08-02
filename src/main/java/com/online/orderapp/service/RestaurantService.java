package com.online.orderapp.service;


import java.util.List;

import com.online.orderapp.entity.Restaurant;

public interface RestaurantService {
	
	public Restaurant createRestaurant(Restaurant restaurant);

	public Restaurant fetchById(int id);

	public List<Restaurant> getAllRestaurant();


}
