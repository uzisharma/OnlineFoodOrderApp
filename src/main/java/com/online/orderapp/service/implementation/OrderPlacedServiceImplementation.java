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
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.CheckoutRepository;
import com.online.orderapp.repository.OrderPlacedRepository;
import com.online.orderapp.service.CartItemService;
import com.online.orderapp.service.CheckoutService;
import com.online.orderapp.service.OrderPlacedService;
import com.online.orderapp.util.PaymentStatus;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderPlacedServiceImplementation implements OrderPlacedService{
	
	private final OrderPlacedRepository orderPlacedRepository;
	private final CartRepository cartRepository;
	private final CheckoutRepository checkoutRepository;
	private final CartItemService cartItemService;
	private final CheckoutService checkoutService;
	
	
@Override
@Transactional
public OrderPlaced placeOrder(Integer cartId, PaymentStatus paymentStatus) {
    Cart cart = cartRepository.findById(cartId)
            .orElseThrow(() -> new NoSuchElementException("Cart with id :" + cartId + " not found"));

    Checkout checkout = checkoutRepository.findByUserId(cart.getUser().getId())
            .orElseThrow(() -> new NoSuchElementException("User not available with id :" + cart.getUser().getId()));

    // ✅ Step 1: Check if order already exists for this checkout
    return orderPlacedRepository.findByCheckoutId(checkout.getId())
            .orElseGet(() -> {
                // ✅ Step 2: Create new order if not found
                String id = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

                OrderPlaced orderPlaced = new OrderPlaced();
                orderPlaced.setId(id);
                orderPlaced.setUser(cart.getUser());
                orderPlaced.setRestaurant(cart.getUserCartItem().getRestaurant());
                orderPlaced.setDeliveryDate(LocalDate.now());
                orderPlaced.setDeliveryTime(LocalTime.now().plusMinutes(40).truncatedTo(ChronoUnit.MINUTES));
                orderPlaced.setPaymentStatus(paymentStatus);
                orderPlaced.setCheckout(checkout);
                orderPlaced.setTotalPrice(checkout.getTotalAmount());

                List<OrderItemNew> orderItems = cart.getUserCartItem().getCartRestaurant()
                        .stream()
                        .map(cartRes -> {
                            OrderItemNew item = new OrderItemNew();
                            item.setFood(cartRes.getFood());
                            item.setQuantity(cartRes.getQuantity());
                            item.setQuantityPrice(cartRes.getQuantityPrice());
                            item.setOrderPlaced(orderPlaced); // link back
                            return item;
                        })
                        .toList();

                orderPlaced.setOrderItem(orderItems);

                OrderPlaced saved = orderPlacedRepository.save(orderPlaced);

                if (paymentStatus == PaymentStatus.COMPLETED) {
                    cartItemService.deleteByCartId(cartId);
                    checkoutService.deleteCheckout(cart.getUser().getId());
                } else if (paymentStatus == PaymentStatus.FAILED) {
                    checkoutService.deleteCheckout(cart.getUser().getId());
                }

                return saved;
            });
}


}
