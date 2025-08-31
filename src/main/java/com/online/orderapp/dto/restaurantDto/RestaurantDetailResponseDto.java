package com.online.orderapp.dto.restaurantDto;

import java.util.List;

import com.online.orderapp.dto.foodDto.FoodResponseDto;

import lombok.Data;

@Data
public class RestaurantDetailResponseDto {

	private Integer id;
	
	private String restaurantName;
	
	private String address;
	
	private Long contactNumber;
	
	private String email;
	
	private Double rating;
	
	private Integer deliveryTime;
	
	private Double deliveryCharges;
	
	private byte[] restaurantImage;
	
	private List<FoodResponseDto> foodList;
	
	private List<String> orderPlacedId;
}
