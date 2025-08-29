package com.online.orderapp.dto.cartDto;

import lombok.Data;

@Data
public class CartResponseDto {

	private Integer id;
	private CartItemResponseDto userCartItem;
	
}
