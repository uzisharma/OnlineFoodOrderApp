package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.online.orderapp.dto.CartItemResponseDto;
import com.online.orderapp.dto.CartResponseDto;
import com.online.orderapp.dto.CartRestaurantResponseDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.CartRestaurant;

@Mapper(componentModel = "spring")
public interface CartMapper {
	
	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
	
	CartResponseDto toDto(Cart cart);
	
	CartItemResponseDto toDto(CartItem cartItem);
	
	@Mapping(source = "restaurant.id", target = "restaurantId")
	CartRestaurantResponseDto toDto(CartRestaurant cartRestaurant);
}
