package com.online.orderapp.service;

import com.online.orderapp.dto.CartDto;

public interface CartService {

	CartDto addToCart(CartDto cartDto);

	CartDto getCartByUser(Integer userId);

	void clearCart(Integer userId);

}
