package com.online.orderapp.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.foodDto.FoodRequestDto;
import com.online.orderapp.dto.foodDto.FoodResponseDto;
import com.online.orderapp.entity.Food;

@Mapper(componentModel = "spring",uses = { BigDecimalMapper.class },unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper {
	
//	@Mapping(source = "restaurants", target = "restaurantId" )
	FoodResponseDto toDto(Food food);
	
//	default List<Integer> map(List<Restaurant> restaurants){
//		if(restaurants == null) return new ArrayList<>();
//		return restaurants
//				.stream()
//				.map(Restaurant::getId)
//				.toList();
//	}
	
	Food toEntity(FoodRequestDto foodRequestDto);


}
