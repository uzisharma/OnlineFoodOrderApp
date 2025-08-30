package com.online.orderapp.service;

import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.util.PaymentStatus;

public interface OrderPlacedService {

	OrderPlaced placeOrder(Integer cartId, PaymentStatus paymentStatus);

}
