package com.online.orderapp.service.implementation;


import java.util.List;

import org.springframework.stereotype.Service;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.CartRestaurant;
import com.online.orderapp.repository.CartItemRepository;
import com.online.orderapp.repository.CartRestaurantRepository;
import com.online.orderapp.service.CartItemService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemServiceImplementation implements CartItemService{

	
	private final CartItemRepository cartItemRepository;
	private final CartRestaurantRepository cartRestaurantRepository;



	
	
	@Override
	@Transactional
	public void deleteByCartId(Integer cartId) {
//		List<Cart> carts = cartRepository.findByCartId(cartId);
		
		List<CartItem> cartItem = cartItemRepository.findByCartId(cartId);
		cartItem.forEach(item->{
			item.setCart(null);
			item.setRestaurant(null);
		});
		
		cartItemRepository.saveAll(cartItem);
		
		List<CartRestaurant> cartRestaurant = cartRestaurantRepository.findByCartItemsId(cartId);
		
		cartRestaurant.forEach(item->{
			item.setCartItems(null);
		});
		
		cartRestaurantRepository.saveAll(cartRestaurant);
		
		cartItemRepository.deleteAll(cartItem);
		// TODO Auto-generated method stub
		
	}

}
