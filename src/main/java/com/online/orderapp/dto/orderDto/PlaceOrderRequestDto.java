package com.online.orderapp.dto.orderDto;

import com.online.orderapp.util.PaymentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlaceOrderRequestDto {

	@NotNull(message = "cart id cannot be null")
	private Integer cartId;
	
	private PaymentStatus paymentStatus;
	
}
