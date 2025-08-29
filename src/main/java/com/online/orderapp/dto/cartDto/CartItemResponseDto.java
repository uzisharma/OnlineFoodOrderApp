package com.online.orderapp.dto.cartDto;

import java.util.List;

import lombok.Data;

@Data
public class CartItemResponseDto {
	private Integer id;
	private List<CartRestaurantResponseDto> cartRestaurant;
	private Integer restaurantId;
	private Double cartPrice;
	private Integer totalCartItem;
}
