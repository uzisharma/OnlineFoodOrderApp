package com.online.orderapp.service;

import org.springframework.data.domain.Page;

import com.online.orderapp.dto.cartDto.CartResponseDto;

public interface CartService{
	CartResponseDto addFoodToCart(Integer userId,Integer restaurantId, Integer foodId, Integer quantity);

	CartResponseDto findCartByUserId(Integer id);



	String deleteCartItemByUserId(Integer id);

	Page<CartResponseDto> getAllCart(int pageNum, int pageSize);
}
