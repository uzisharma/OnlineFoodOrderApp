package com.online.orderapp.service;

import org.springframework.data.domain.Page;

import com.online.orderapp.dto.foodDto.FoodResponseDto;
import com.online.orderapp.entity.Food;

public interface FoodService {

	FoodResponseDto createFood(Food food);
	
	FoodResponseDto getFoodById(Integer id);
	
	Page<FoodResponseDto> getAllFood(int pageNum, int pageSize);
	
	FoodResponseDto updateFood(Food food, Integer id);
	
	void deleteFood(Integer id);
}
