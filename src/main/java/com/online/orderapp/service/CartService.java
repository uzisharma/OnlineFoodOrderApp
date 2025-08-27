package com.online.orderapp.service;

import com.online.orderapp.entity.Cart;

public interface CartService{
	Cart addFoodToCart(Integer userId, Integer foodId, Integer quantity);
}
