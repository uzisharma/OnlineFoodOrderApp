package com.online.orderapp.service;

import com.online.orderapp.dto.CartResponseDto;

public interface CartService{
	CartResponseDto addFoodToCart(Integer userId,Integer restaurantId, Integer foodId, Integer quantity);

	CartResponseDto findCartByUserId(Integer id);



	String deleteCartById(Integer id);
}
