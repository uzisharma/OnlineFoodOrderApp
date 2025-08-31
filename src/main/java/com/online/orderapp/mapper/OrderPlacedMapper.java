package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.orderDto.OrderPlacedDto;
import com.online.orderapp.entity.OrderPlaced;

@Mapper(componentModel = "spring",  uses = { CheckoutMapper.class, BigDecimalMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderPlacedMapper {

	@Mapping(source = "user.userName", target="userName")
	@Mapping(source = "restaurant.id", target="restaurantId")
	@Mapping(source = "restaurant.restaurantName", target="restaurantName")
	@Mapping(source = "checkout", target="checkoutResponseDto")
	OrderPlacedDto toDto(OrderPlaced orderPlaced);
}
