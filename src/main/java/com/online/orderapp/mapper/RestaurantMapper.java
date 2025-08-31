package com.online.orderapp.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.restaurantDto.RestaurantDetailResponseDto;
import com.online.orderapp.dto.restaurantDto.RestaurantRequestDto;
import com.online.orderapp.dto.restaurantDto.RestaurantResponseDto;
import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.entity.Restaurant;

@Mapper(componentModel = "spring", 
uses = { BigDecimalMapper.class , FoodMapper.class},
unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantMapper {
	
	RestaurantResponseDto toDto(Restaurant restaurant);
	
	@Mapping(source = "food", target="foodList")
	@Mapping(source = "orderPlaced", target="orderPlacedId")
	RestaurantDetailResponseDto toDetailDto(Restaurant restaurant);
	
	default List<String> mapOrderPlacedList(List<OrderPlaced> orders){
		if(orders == null) return null;
		return orders.stream()
				.map(OrderPlaced::getId)
				.toList();
	}
	
	Restaurant toEntity(RestaurantRequestDto restaurantRequestDto);
	
	
	Restaurant toRestaurantEntity(RestaurantDetailResponseDto restaurantDetailResponseDto);
}
