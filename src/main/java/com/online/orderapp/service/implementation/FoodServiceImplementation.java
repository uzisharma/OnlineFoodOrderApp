package com.online.orderapp.service.implementation;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.online.orderapp.entity.Food;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.service.FoodService;

@Service
public class FoodServiceImplementation implements FoodService {


	@Autowired
	private FoodRepository foodRepository;

	
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
		foodRepository.delete(food);
	}

}
