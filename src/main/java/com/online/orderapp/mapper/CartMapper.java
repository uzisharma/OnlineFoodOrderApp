package com.online.orderapp.mapper;

import java.util.List;
import java.util.NoSuchElementException;

import com.online.orderapp.dto.CartDto;
import com.online.orderapp.dto.CartItemDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.entity.User;

public class CartMapper {
	
	public static CartDto toDto(Cart cart) {
		CartDto dto = new CartDto();
		dto.setId(cart.getId());
		dto.setRestuarantId(cart.getRestaurant().getId());
		dto.setUserId(cart.getUser().getId());
		
		
		List<CartItemDto> items = cart.getCartItems().stream().map(ci ->{
			CartItemDto i = new CartItemDto();
			i.setFoodId(ci.getFood().getId());
			i.setQuantity(ci.getQuantity());
			return i;
		}).toList();
		
		dto.setOrderItem(items);
		
		
		return dto;
	}
	
	
	public static Cart toEntity(CartDto dto, User user, Restaurant restaurant, List<Food> foods) {
		Cart cart = new Cart();
		cart.setId(dto.getId());
		cart.setRestaurant(restaurant);
		cart.setUser(user);
		
		List<CartItem> items = dto.getOrderItem().stream().map( i-> {
			CartItem ci = new CartItem();
			ci.setFood(foods.stream()
					.filter(f-> f.getId().equals(i.getFoodId()))
					.findFirst()
					.orElseThrow(()-> new NoSuchElementException("Food Not Found"))
					);
			ci.setQuantity(i.getQuantity());
			ci.setCart(cart);
			return ci;
		}).toList();
		
		cart.setCartItems(items);
		return cart;
	}
}
