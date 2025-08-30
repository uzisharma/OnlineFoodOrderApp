package com.online.orderapp.service.implementation;


import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.online.orderapp.entity.Cart;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.service.CartItemService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService{
	
	private final CartRepository cartRepository;

	
	@Override
	public void deleteByCartId(Integer cartId) {
		
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(()->new NoSuchElementException("cart not available"));
		cart.setUserCartItem(null);
		cartRepository.save(cart);
		
	}

}
