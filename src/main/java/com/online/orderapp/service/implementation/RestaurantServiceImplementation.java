package com.online.orderapp.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.repository.RestaurantRepository;
import com.online.orderapp.service.RestaurantService;

@Service
public class RestaurantServiceImplementation implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	
}
