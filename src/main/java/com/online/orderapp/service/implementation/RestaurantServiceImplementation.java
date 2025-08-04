package com.online.orderapp.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public List<Restaurant> getAllRestaurant() {
		return restaurantRepository.findAll();
	}

	@Override
	public Page<?> getAllRestaurants(int pageNum, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page<?> page = restaurantRepository.findAll(pageable);
		return page;
	}

	@Override
	public Restaurant updateRestaurant(int id, Restaurant restaurant) {
		Restaurant fetchedRes= restaurantRepository.findById(id).orElseThrow(()->new NoSuchElementException("Restaurant with ID : "+id+" not found"));
		if(fetchedRes!=null) {
			fetchedRes.setEmail(restaurant.getEmail());
			fetchedRes.setAddress(restaurant.getAddress());
			fetchedRes.setContactNumber(restaurant.getContactNumber());
			fetchedRes.setRestaurantName(restaurant.getRestaurantName());
			restaurantRepository.save(fetchedRes);
		}
		return fetchedRes;
	}
	
	



	
}
