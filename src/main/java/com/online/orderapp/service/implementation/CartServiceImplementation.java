package com.online.orderapp.service.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.CartRestaurant;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.User;
import com.online.orderapp.repository.CartItemRepository;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.CartRestaurantRepository;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.UserRepository;
import com.online.orderapp.service.CartService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService{
	private final UserRepository userRepo;
	private final FoodRepository foodRepo;
	private final CartRepository cartRepo;
	private final CartItemRepository cartItemRepo;
	private final CartRestaurantRepository cartRestaurantRepo;

	@Override
	@Transactional
	public Cart addFoodToCart(Integer userId, Integer foodId, Integer quantity) {
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new NoSuchElementException("User Not Found"));
		
		Food food = foodRepo.findById(foodId)
				.orElseThrow(()-> new NoSuchElementException("Food not found"));
		
		//Get or create cart
		Cart cart = user.getUserCart();
		if(cart==null) {
			cart = new Cart();
			cart.setUser(user);
			
			CartItem cartItem = new CartItem();
			cart.setCartItem(cartItem);
			cartRepo.save(cart);
		}
		CartItem cartItem = cart.getCartItem();
		
		//Check if food already exist in cart 
		Optional<CartRestaurant> existingItem = cartItem.getCartRestaurant()
				.stream()
				.filter(cr -> cr.getFood().getId().equals(foodId))
				.findFirst();
		
		if(existingItem.isPresent()) {
			CartRestaurant cartRestaurant = existingItem.get();
			cartRestaurant.setQuantity(cartRestaurant.getQuantity() + quantity);
			cartRestaurant.setQuantityPrice(cartRestaurant.getQuantity() * (double)food.getPrice() );
			cartRestaurantRepo.save(cartRestaurant);
		}else {
			CartRestaurant cartRestaurant = new CartRestaurant();
			cartRestaurant.setCartItem(cartItem);
			cartRestaurant.setFood(food);
			cartRestaurant.setQuantity(quantity);
			cartRestaurant.setQuantityPrice(quantity * (double)food.getPrice());
			
			
			cartItem.getCartRestaurant().add(cartRestaurant);
			cartRestaurantRepo.save(cartRestaurant);
		}
		
		double total = cartItem.getCartRestaurant()
				.stream()
				.mapToDouble(CartRestaurant::getQuantityPrice)
				.sum();
		cartItem.setCartPrice(total);
		
		cartItemRepo.save(cartItem);
		return cartRepo.save(cart);
				
	}

}
