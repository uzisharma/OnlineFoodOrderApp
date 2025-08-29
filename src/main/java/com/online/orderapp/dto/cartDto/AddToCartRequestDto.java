package com.online.orderapp.dto.cartDto;

import lombok.Data;

@Data
public class AddToCartRequestDto {

	private Integer userId;
	private Integer foodId;
	private Integer quantity;
	private Integer restaurantId;
}
