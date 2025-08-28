package com.online.orderapp.dto;

import lombok.Data;

@Data
public class CartResponseDto {

	private Integer id;
	private CartItemResponseDto userCartItem;
	
}
