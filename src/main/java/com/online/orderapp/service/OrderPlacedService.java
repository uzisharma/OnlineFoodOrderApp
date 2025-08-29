package com.online.orderapp.service;

import com.online.orderapp.entity.OrderPlaced;

public interface OrderPlacedService {

	OrderPlaced placeOrder(Integer cartId);

}
