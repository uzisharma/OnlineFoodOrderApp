package com.online.orderapp.dto.foodDto;

import lombok.Data;

@Data
public class FoodRequestDto {

	private String foodName;
	private String description;
	
	private double price;
	
}
