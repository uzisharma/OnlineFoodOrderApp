package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.checkoutDto.CheckoutResponseDto;
import com.online.orderapp.entity.Checkout;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckoutMapper {
		
	
	@Mapping(source = "cart.id", target = "cartId")
	@Mapping(source = "cart.user.id", target= "userId")
	CheckoutResponseDto toDto(Checkout checkout);

	
}
