package com.online.orderapp.dto.orderDto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.online.orderapp.util.PaymentStatus;

import lombok.Data;

@Data
public class AllOrderResponseDto {
	
	private String id;
	
	private String userName;
	
	private String restaurantName;
	
	private Double totalPrice;
	
	private LocalDate deliveryDate;
	
	private LocalTime deliveryTime;
	
	private PaymentStatus paymentStatus;
}
