package com.online.orderapp.dto.cartDto;

import com.online.orderapp.entity.Food;

import lombok.Data;

@Data
public class CartRestaurantResponseDto {

	private Integer id;
//	private Integer restaurantId;
	private Food food;
	private Integer quantity;
	private Double quantityPrice;
}
