package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.checkoutDto.CheckoutRequestDto;
import com.online.orderapp.dto.checkoutDto.CheckoutResponseDto;
import com.online.orderapp.entity.Checkout;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckoutMapper {
		
	Checkout toEntity(CheckoutRequestDto checkoutRequest);
	
	CheckoutResponseDto toDto(Checkout checkout);

	
}
