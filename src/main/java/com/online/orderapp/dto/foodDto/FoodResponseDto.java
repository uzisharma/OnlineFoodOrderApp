package com.online.orderapp.dto.foodDto;

import java.util.List;

import lombok.Data;

@Data
public class FoodResponseDto {

	private Integer id;
	private String foodName;
	private String description;
	private Double price;
	
	private byte[] foodImage;
	
	private List<Integer> restaurantId;
}
