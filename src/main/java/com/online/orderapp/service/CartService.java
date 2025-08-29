package com.online.orderapp.service;

import com.online.orderapp.dto.cartDto.CartResponseDto;

public interface CartService{
	CartResponseDto addFoodToCart(Integer userId,Integer restaurantId, Integer foodId, Integer quantity);

	CartResponseDto findCartByUserId(Integer id);



	String deleteCartItemByUserId(Integer id);
}
