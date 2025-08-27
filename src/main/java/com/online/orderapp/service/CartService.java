package com.online.orderapp.service;

import com.online.orderapp.dto.CartResponseDto;

public interface CartService{
	CartResponseDto addFoodToCart(Integer userId,Integer restaurantId, Integer foodId, Integer quantity);
}
