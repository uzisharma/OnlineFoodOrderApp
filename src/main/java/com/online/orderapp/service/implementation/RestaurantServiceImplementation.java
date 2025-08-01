package com.online.orderapp.service.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;

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

	@Override
	public Restaurant fetchById(int id) {
//		Optional<Restaurant> response= restaurantRepository.findById(id);
//		if(response.isPresent()) {
//			return response.get();
//		}else {
//			throw new NoSuchElementException("Restaurant with ID : "+id+" not found");
//		}
		return restaurantRepository.findById(id).orElseThrow(()->new NoSuchElementException("Restaurant with ID : "+id+" not found"));
	}

	
}
