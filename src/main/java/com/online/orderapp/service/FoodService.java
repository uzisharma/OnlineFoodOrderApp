package com.online.orderapp.service;

import org.springframework.data.domain.Page;

import com.online.orderapp.entity.Food;

public interface FoodService {

	Food createFood(Food food);
	
	Food getFoodById(Integer id);
	
	Page<Food> getAllFood(int pageNum, int pageSize);
	
	Food updateFood(Food food, Integer id);
	
	void deleteFood(Integer id);
}
