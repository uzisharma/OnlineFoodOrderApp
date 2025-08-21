package com.online.orderapp.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.CartDto;
import com.online.orderapp.dto.CartItemDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.entity.User;
import com.online.orderapp.mapper.CartMapper;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.RestaurantRepository;
import com.online.orderapp.repository.UserRepository;
import com.online.orderapp.service.CartService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImplementation implements CartService {
	
	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final RestaurantRepository restaurantRepository;
	private final FoodRepository foodRepository;
	

	@Override
	public CartDto addToCart(CartDto cartDto) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findById(cartDto.getUserId())
				.orElseThrow(()-> new NoSuchElementException("User Not Found"));
		
		Restaurant restaurant = restaurantRepository.findById(cartDto.getRestuarantId())
				.orElseThrow(()-> new NoSuchElementException("Restaurant not found"));
		
		
		List<Food> foods = foodRepository.findAllById(cartDto.getOrderItem()
				.stream()
				.map(CartItemDto::getFoodId)
				.toList()
				);
		
		Cart cart = CartMapper.toEntity(cartDto, user, restaurant, foods)
				;
		Cart saved = cartRepository.save(cart);
		return CartMapper.toDto(saved);
	}
	
	@Override
    public CartDto getCartByUser(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return CartMapper.toDto(cart);
    }

	@Override
    public void clearCart(Integer userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }

}
