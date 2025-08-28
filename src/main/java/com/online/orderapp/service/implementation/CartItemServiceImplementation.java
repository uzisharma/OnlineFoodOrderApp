package com.online.orderapp.service.implementation;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.online.orderapp.entity.CartItem;
import com.online.orderapp.repository.CartItemRepository;
import com.online.orderapp.service.CartItemService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService{
	
	private final CartItemRepository cartItemRepository;

	@Override
	public void deleteCartItem(Integer cartItemId) {

		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(()-> new NoSuchElementException("Cart Item not found with id :"+ cartItemId));
		cartItemRepository.delete(cartItem);
	}

}
