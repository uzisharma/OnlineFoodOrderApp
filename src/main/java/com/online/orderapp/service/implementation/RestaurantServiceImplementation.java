package com.online.orderapp.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.online.orderapp.dto.restaurantDto.RestaurantDetailResponseDto;
import com.online.orderapp.dto.restaurantDto.RestaurantRequestDto;
import com.online.orderapp.dto.restaurantDto.RestaurantResponseDto;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.mapper.RestaurantMapper;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.RestaurantRepository;
import com.online.orderapp.service.RestaurantService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RestaurantServiceImplementation implements RestaurantService {
	
	private final RestaurantRepository restaurantRepository;	
	private final FoodRepository foodRepository;
	private final RestaurantMapper restaurantMapper;
	
	
	
	@Override
	public RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurant) {
		return restaurantMapper.toDto(restaurantRepository.save(restaurantMapper.toEntity(restaurant)));
	}

	
	@Override
	public RestaurantDetailResponseDto fetchById(int id) {
		 Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(()->new NoSuchElementException("Restaurant with ID : "+id+" not found"));
		 
		 return restaurantMapper.toDetailDto(restaurant);
	}



	@Override
	public Page<RestaurantResponseDto> getAllRestaurants(int pageNum, int pageSize, String sortBy) {
		Sort sort = Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
		Page<Restaurant> page = restaurantRepository.findAll(pageable);
		
		 
		return page.map(restaurantMapper::toDto);
	}

	
	
	@Override
	public RestaurantResponseDto updateRestaurant(int id, RestaurantRequestDto restaurant) {
		Restaurant fetchedRes= restaurantRepository.findById(id)
				.orElseThrow(()->new NoSuchElementException("Restaurant with ID : "+id+" not found"));
		
		if(fetchedRes!=null) {
			fetchedRes.setEmail(restaurant.getEmail());
			fetchedRes.setAddress(restaurant.getAddress());
			fetchedRes.setContactNumber(restaurant.getContactNumber());
			fetchedRes.setRestaurantName(restaurant.getRestaurantName());
			restaurantRepository.save(fetchedRes);
		}
		return restaurantMapper.toDto(fetchedRes);
	}

	@Override
	public void deleteRestaurant(Integer id) {
		 Restaurant restaurant = restaurantRepository.findById(id)
					.orElseThrow(()->new NoSuchElementException("Restaurant with ID : "+id+" not found"));
		
		restaurantRepository.delete(restaurant);
	}
	
	@Override
	public Restaurant assignFood(Integer restaurantId, Set<Integer> foodId) {
		Restaurant restaurant = restaurantMapper.toRestaurantEntity(fetchById(restaurantId));
		
		List<Food> foodItems = new ArrayList<>();
		
		for(Integer id : foodId) {
			Food food = foodRepository.findById(id).orElseThrow(()->new NoSuchElementException("Food with ID: "+id+" not found"));
			foodItems.add(food);
		}
		
		restaurant.setFood(foodItems);
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public List<Food> findFoodByRestaurantId(Integer id) {
		// TODO Auto-generated method stub
		List<Food> food = restaurantRepository.findFoodByRestaurantId(id);
		if(food==null || food.size()==0) {
			throw new NoSuchElementException("Restaurant with Id : "+id+" not found or the food is not assigned to the restaurant");
		}else {
			return food;
		}
	}



	
	



	
}
