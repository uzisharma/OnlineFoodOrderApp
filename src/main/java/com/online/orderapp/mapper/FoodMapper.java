package com.online.orderapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.online.orderapp.dto.foodDto.FoodResponseDto;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper {
	
	@Mapping(source = "restaurants", target = "restaurantId" )
	FoodResponseDto toDto(Food food);
	
	default List<Integer> map(List<Restaurant> restaurants){
		if(restaurants == null) return new ArrayList<>();
		return restaurants
				.stream()
				.map(Restaurant::getId)
				.toList();
	}


}
