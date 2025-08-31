package com.online.orderapp.dto.restaurantDto;

import lombok.Data;

@Data
public class RestaurantResponseDto {

	private Integer id;
	
	private String restaurantName;
	
	private String address;
	
	private Long contactNumber;
	
	private String email;
	
	private Double rating;
	
	private Integer deliveryTime;
	
	private Double deliveryCharges;
	
	private byte[] restaurantImage;
}
