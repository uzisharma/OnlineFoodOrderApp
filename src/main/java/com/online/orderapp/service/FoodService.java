package com.online.orderapp.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.online.orderapp.dto.foodDto.FoodRequestDto;
import com.online.orderapp.dto.foodDto.FoodResponseDto;

public interface FoodService {

	FoodResponseDto createFood(FoodRequestDto request);
	
	FoodResponseDto getFoodById(Integer id);
	
	Page<FoodResponseDto> getAllFood(int pageNum, int pageSize);
	
	FoodResponseDto updateFood(FoodRequestDto request, Integer id);
	
	void deleteFood(Integer id);

	String uploadImage(MultipartFile image, Integer id) throws IOException;
}
