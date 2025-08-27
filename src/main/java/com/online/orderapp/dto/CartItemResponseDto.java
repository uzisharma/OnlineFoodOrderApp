package com.online.orderapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class CartItemResponseDto {
	private Integer id;
	private List<CartRestaurantResponseDto> cartRestaurant;
	private Double cartPrice;
	private Integer totalQuantity;
}
