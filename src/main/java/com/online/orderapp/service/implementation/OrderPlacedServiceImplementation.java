package com.online.orderapp.service.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.Checkout;
import com.online.orderapp.entity.OrderItemNew;
import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.entity.Restaurant;
import com.online.orderapp.entity.User;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.CheckoutRepository;
import com.online.orderapp.repository.OrderPlacedRepository;
import com.online.orderapp.service.OrderPlacedService;
import com.online.orderapp.util.PaymentStatus;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderPlacedServiceImplementation implements OrderPlacedService{
	
	private final OrderPlacedRepository orderPlacedRepository;
	private final CartRepository cartRepository;
	private final CheckoutRepository checkoutRepository;
	
	
	@Override
	public OrderPlaced placeOrder(Integer cartId) {
		// TODO Auto-generated method stub
		
		String id = "ORD-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
		OrderPlaced orderPlaced = new OrderPlaced();
		
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(()-> new NoSuchElementException("Cart with id :" + cartId + "not found"));
		
		Checkout checkout = checkoutRepository.findByUserId(cart.getUser().getId())
				.orElseThrow(()-> new NoSuchElementException("User not available with id :"+ cart.getUser().getId()));
		
		User user = cart.getUser();
		
		Restaurant restaurant = cart.getUserCartItem().getRestaurant();
		
		List<OrderItemNew> orderItems = cart.getUserCartItem().getCartRestaurant()
				.stream()
				.map(cartRes ->{
					OrderItemNew item = new OrderItemNew();
					item.setFood(cartRes.getFood());
					item.setQuantity(cartRes.getQuantity());
					item.setQuantityPrice(cartRes.getQuantityPrice());
					return item;
				})
				.toList();
		

		orderPlaced.setId(id);
		orderPlaced.setUser(user);
		orderPlaced.setRestaurant(restaurant);
		orderPlaced.setOrderItem(orderItems);
		orderPlaced.setDeliveryDate(LocalDate.now());
		orderPlaced.setDeliveryTime(LocalTime.now().plusMinutes(40).truncatedTo(ChronoUnit.MINUTES));
		orderPlaced.setPaymentStatus(PaymentStatus.COMPLETED);
		orderPlaced.setCheckout(checkout);
		orderPlaced.setTotalPrice(checkout.getTotalAmount());
		
		orderItems.forEach(item-> item.setOrderPlaced(orderPlaced));
		return orderPlacedRepository.save(orderPlaced);
	}
	
	

}
