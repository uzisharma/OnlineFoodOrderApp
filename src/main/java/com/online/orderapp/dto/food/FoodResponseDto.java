package com.online.orderapp.dto.food;

import java.util.List;

import lombok.Data;

@Data
public class FoodResponseDto {

	private Integer id;
	private String foodName;
	private String description;
	private float price;
	
	private List<Integer> restaurantId;
}
