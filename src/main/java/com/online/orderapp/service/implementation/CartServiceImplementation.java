package com.online.orderapp.service.implementation;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.cartDto.CartResponseDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
import com.online.orderapp.entity.CartRestaurant;
import com.online.orderapp.entity.Checkout;
import com.online.orderapp.entity.Food;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.entity.User;
import com.online.orderapp.mapper.CartMapper;
import com.online.orderapp.repository.CartItemRepository;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.CartRestaurantRepository;
import com.online.orderapp.repository.CheckoutRepository;
import com.online.orderapp.repository.FoodRepository;
import com.online.orderapp.repository.RestaurantRepository;
import com.online.orderapp.repository.UserRepository;
import com.online.orderapp.service.CartService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImplementation implements CartService{
	private final UserRepository userRepo;
	private final RestaurantRepository restaurantRepo;
	private final FoodRepository foodRepo;
	private final CartRepository cartRepo;
	private final CartItemRepository cartItemRepo;
	private final CartRestaurantRepository cartRestaurantRepo;
	private final CheckoutRepository checkoutRepo;
	private final CartMapper cartMapper;

	@Override
	@Transactional
	public CartResponseDto addFoodToCart(Integer userId,Integer restaurantId, Integer foodId, Integer quantity) {
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new NoSuchElementException("User Not Found"));
		
		Food food = foodRepo.findById(foodId)
				.orElseThrow(()-> new NoSuchElementException("Food not found"));
		
		Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow(()->new NoSuchElementException("restaurant with id :"+restaurantId+" not found"));
		
		//Get or create cart
		Cart cart = user.getUserCart();
		if(cart==null) {
			cart = new Cart();
			cart.setUser(user);
			CartItem cartItem = new CartItem();
			cart.setUserCartItem(cartItem);
			cartItem.setCart(cart);
			cartRepo.save(cart);
		}
		
		user.setUserCart(cart);
		userRepo.save(user);
		
		CartItem cartItem = cart.getUserCartItem();
		if(cartItem.getCartRestaurant() == null) {
			cartItem.setCartRestaurant(new ArrayList<>());
		}
		
		//Check if food already exist in cart 
		Optional<CartRestaurant> existingItem = cartItem.getCartRestaurant()
				.stream()
				.filter(cr -> cr.getFood().getId().equals(foodId))
				.findFirst();
		
		if(existingItem.isPresent()) {
			CartRestaurant cartRestaurant = existingItem.get();
//			cartRestaurant.setRestaurant(restaurant);
			cartRestaurant.setQuantity( quantity);
			cartRestaurant.setQuantityPrice(cartRestaurant.getQuantity() * (double)food.getPrice() );
			cartRestaurantRepo.save(cartRestaurant);
		}else {
			CartRestaurant cartRestaurant = new CartRestaurant();
			cartRestaurant.setCartItems(cartItem);
//			cartRestaurant.setRestaurant(restaurant);
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
		int totalCartItem = (int)cartItem.getCartRestaurant()
				.stream()
				.count();
		cartItem.setTotalCartItem(totalCartItem);
		cartItem.setRestaurant(restaurant);
		
		cartItemRepo.save(cartItem);
//		return cartRepo.save(cart);
		return cartMapper.toDto(cart);
				
	}

	@Override
	public CartResponseDto findCartByUserId(Integer id) {
		// TODO Auto-generated method stub
		Cart cart = cartRepo.findByUserId(id)
				.orElseThrow(()-> new NoSuchElementException("User with id: "+id +" not found or Cart is null"));
		return cartMapper.toDto(cart);
	}




	@Override
	@Transactional
	public String deleteCartItemByUserId(Integer id) {
		User user = userRepo.findById(id)
				.orElseThrow(()->new NoSuchElementException("User not found with id :"+id));
		Checkout checkout = checkoutRepo.findByUserId(user.getId())
				.orElseThrow(()->new NoSuchElementException("User not found with id :"+user.getId()));
		
		if (user.getUserCart() == null) {
		    throw new NoSuchElementException("Cart does not exist for user id: " + id);
		}
		
		Cart cart = cartRepo.findById(user.getUserCart().getId())
				.orElseThrow(()->new NoSuchElementException("Cart not found with user id: "+ id));

		user.setUserCart(null);
		checkout.setCart(null);
		cartRepo.delete(cart);

		return "Cart Deleted with id: "+id;
	}

}
