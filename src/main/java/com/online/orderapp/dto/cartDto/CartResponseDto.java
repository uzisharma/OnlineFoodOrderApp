package com.online.orderapp.dto.cartDto;

import lombok.Data;

@Data
public class CartResponseDto {

	private Integer cartId;
	private Integer userId;
	private String userName;
	private CartItemResponseDto userCartItem;
	
}
