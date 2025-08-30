package com.online.orderapp.dto.orderDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.online.orderapp.dto.checkoutDto.CheckoutResponseDto;
import com.online.orderapp.entity.OrderItemNew;
import com.online.orderapp.util.PaymentStatus;

import lombok.Data;

@Data
public class OrderPlacedDto {
	
	private String id;
	
	private String userName;
	
	private Integer restaurantId;
	
	private String restaurantName;
	
	private List<OrderItemNew> orderItem;
	
	private CheckoutResponseDto checkoutResponseDto;
	
	private Double totalPrice;
	
	private LocalDate deliveryDate;
	
	private LocalTime deliveryTime;
	
	private PaymentStatus paymentStatus;
}
