package com.online.orderapp.service;

import org.springframework.data.domain.Page;

import com.online.orderapp.dto.foodDto.FoodRequestDto;
import com.online.orderapp.dto.foodDto.FoodResponseDto;

public interface FoodService {

	FoodResponseDto createFood(FoodRequestDto request);
	
	FoodResponseDto getFoodById(Integer id);
	
	Page<FoodResponseDto> getAllFood(int pageNum, int pageSize);
	
	FoodResponseDto updateFood(FoodRequestDto request, Integer id);
	
	void deleteFood(Integer id);
}
