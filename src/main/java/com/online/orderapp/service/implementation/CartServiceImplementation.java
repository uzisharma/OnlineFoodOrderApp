package com.online.orderapp.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.CartDto;
import com.online.orderapp.dto.CartItemResponseDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.CartItem;
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

    User user = userRepository.findById(cartDto.getUserId())
            .orElseThrow(() -> new NoSuchElementException("User Not Found"));

    Restaurant restaurant = restaurantRepository.findById(cartDto.getRestuarantId())
            .orElseThrow(() -> new NoSuchElementException("Restaurant not found"));

    List<Food> foods = foodRepository.findAllById(
            cartDto.getOrderItem()
                   .stream()
                   .map(CartItemResponseDto::getFoodId)
                   .toList()
    );

    // fetch existing cart or create new one
    Cart cart = cartRepository.findByUserId(cartDto.getUserId())
            .orElseGet(() -> {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setRestaurant(restaurant);
                newCart.setCartItems(new ArrayList<>());
                return newCart;
            });

    // ✅ enforce same restaurant
    if (cart.getRestaurant() != null && !cart.getRestaurant().getId().equals(restaurant.getId())) {
        throw new IllegalStateException("You can only add items from one restaurant at a time. Please clear the cart first.");
    }

    // 🔑 Merge new items into existing list
    for (var dtoItem : cartDto.getOrderItem()) {
        Food food = foods.stream()
                .filter(f -> f.getId().equals(dtoItem.getFoodId()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Food Not Found"));

        // check if already exists in cart
        CartItem existingItem = cart.getCartItems().stream()
                .filter(ci -> ci.getFood().getId().equals(food.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // ✅ update quantity & price
            existingItem.setQuantity(existingItem.getQuantity() + dtoItem.getQuantity());
            existingItem.setPrice(food.getPrice() * existingItem.getQuantity());
        } else {
            // ✅ create new item
            CartItem newItem = new CartItem();
            newItem.setFood(food);
            newItem.setQuantity(dtoItem.getQuantity());
            newItem.setPrice(food.getPrice() * dtoItem.getQuantity());
            newItem.setCart(cart);
            cart.getCartItems().add(newItem);
        }
    }

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
