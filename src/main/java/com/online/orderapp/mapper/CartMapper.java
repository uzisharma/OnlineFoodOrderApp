package com.online.orderapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.online.orderapp.dto.cartDto.CartItemResponseDto;
import com.online.orderapp.dto.cartDto.CartResponseDto;
import com.online.orderapp.dto.cartDto.CartRestaurantResponseDto;
import com.online.orderapp.dto.orderDto.OrderSummaryDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.CartRestaurant;

@Mapper(componentModel = "spring", uses = { BigDecimalMapper.class })
public interface CartMapper {
	
	@Mapping(source = "userCartItem", target="userCartItem")
	CartResponseDto toDto(Cart cart);
	
	@Mapping(source = "restaurant.id", target="restaurantId")
	CartItemResponseDto toDto(CartItem cartItem);
	

	CartRestaurantResponseDto toDto(CartRestaurant cartRestaurant);
	

	@Mapping(source = "food.foodName", target="foodName")
	OrderSummaryDto toOrderSummaryDto(CartRestaurant cartLine);
}
