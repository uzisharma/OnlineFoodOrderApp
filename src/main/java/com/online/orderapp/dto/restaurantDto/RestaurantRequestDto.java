package com.online.orderapp.dto.restaurantDto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestaurantRequestDto {
	
	private String restaurantName;
	
	private String address;
	
	private Long contactNumber;
	
	private String email;
	
	private BigDecimal rating;
	
	private Integer deliveryTime;
	
	private BigDecimal deliveryCharges;
	
//	private byte[] restaurantImage;
}
