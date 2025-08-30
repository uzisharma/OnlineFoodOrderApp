package com.online.orderapp.dto.checkoutDto;

import java.util.List;

import com.online.orderapp.dto.orderDto.OrderSummaryDto;

import lombok.Data;

@Data
public class CheckoutResponseDto {
	
	private Integer id;
	
	private String restaurantName;
	
	private Integer userId;
	
	private Integer cartId;
	
	private Double gstPercent;
	
	private Double gstAmount;
	
	private Double originalAmount;
	
	private Double totalAmount;
	
	private Integer itemQuantity;
		
	private List<OrderSummaryDto> orderSummary;
}
