package com.online.orderapp.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.RestaurantRepository;
import com.online.orderapp.service.FoodService;

@Service
public class FoodServiceImplementation implements FoodService {

    private final RestaurantRepository restaurantRepository;


	@Autowired
	private FoodRepository foodRepository;


    FoodServiceImplementation(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

	
	@Override
	public Food createFood(Food food) {
		return foodRepository.save(food);
	}

	@Override
	public Food getFoodById(Integer id) {
		// TODO Auto-generated method stub
		return foodRepository.findById(id).orElseThrow(()->new NoSuchElementException("Food is not present"));
	}

	@Override
	public Page<Food> getAllFood(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		Page<Food> page = foodRepository.findAll(pageable);
		return page;
	}

	@Override
	public Food updateFood(Food food, Integer id) {
		Food fetchedFood = getFoodById(id);
		if(fetchedFood!=null) {
			fetchedFood.setFoodName(food.getFoodName());
			fetchedFood.setDescription(food.getDescription());
			fetchedFood.setPrice(food.getPrice());
			
		}
		return fetchedFood;
	}

	@Override
	public void deleteFood(Integer id) {
		Food food = getFoodById(id);
		List<Restaurant> restaurants = food.getRestaurants();
		
		if(restaurants.size()==0) {
			foodRepository.delete(food);
			return;
		}
		
		for(Restaurant restaurant : restaurants) {
			restaurant.getFood().remove(food);
		}
		restaurantRepository.saveAll(restaurants);
		foodRepository.delete(food);
	}

}
