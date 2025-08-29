package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.online.orderapp.dto.cartDto.CartItemResponseDto;
import com.online.orderapp.dto.cartDto.CartResponseDto;
import com.online.orderapp.dto.cartDto.CartRestaurantResponseDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.CartRestaurant;

@Mapper(componentModel = "spring")
public interface CartMapper {
	
	
	CartResponseDto toDto(Cart cart);
	
	@Mapping(source = "restaurant.id", target="restaurantId")
	CartItemResponseDto toDto(CartItem cartItem);
	
	CartRestaurantResponseDto toDto(CartRestaurant cartRestaurant);
}
