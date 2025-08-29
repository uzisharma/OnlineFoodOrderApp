package com.online.orderapp.dto.checkoutDto;

import lombok.Data;

@Data
public class CheckoutResponseDto {
	
	private Integer id;
	
	private Integer userId;
	
	private Integer cartId;
	
	private Double totalAmount;
	
	private Integer itemQuantity;
	
	
}
