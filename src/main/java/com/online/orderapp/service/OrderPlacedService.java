package com.online.orderapp.service;

import org.springframework.data.domain.Page;

import com.online.orderapp.dto.orderDto.OrderPlacedResponseDto;
import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.util.PaymentStatus;

public interface OrderPlacedService {

	OrderPlaced placeOrder(Integer cartId, PaymentStatus paymentStatus);

	Page<OrderPlacedResponseDto> getAllOrders(int pageNum, int pageSize, String sortBy);

	Page<OrderPlacedResponseDto> getOrderByUserId(Integer userId, int pageNum, int pageSize);

}
