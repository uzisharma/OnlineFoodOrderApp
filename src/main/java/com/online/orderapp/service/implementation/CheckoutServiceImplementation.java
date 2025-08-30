package com.online.orderapp.service.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.checkoutDto.CheckoutRequestDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.Checkout;
import com.online.orderapp.entity.OrderPlaced;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.CheckoutRepository;
import com.online.orderapp.repository.OrderPlacedRepository;
import com.online.orderapp.service.CheckoutService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckoutServiceImplementation implements CheckoutService{
	
	private final CheckoutRepository checkoutRepository;
	private final CartRepository cartRepository;
	private final OrderPlacedRepository orderPlacedRepository;
	
	
	@Override
	public Checkout addToCheckout(CheckoutRequestDto checkoutRequestDto) {
		//cartId
		Optional<Checkout> previousCheckout = checkoutRepository.findByCartId(checkoutRequestDto.getCartId());
		

		
		Cart cart = cartRepository.findById(checkoutRequestDto.getCartId())
				.orElseThrow(()-> new NoSuchElementException("cart is not available with the id :"+checkoutRequestDto.getCartId()));
		
		if(previousCheckout.isPresent()) {
			deleteCheckout(cart.getUser().getId());
		}
		
		if(cart.getUserCartItem() == null) {
			throw new NoSuchElementException("CartItem is not available");
		}
		Checkout checkout = new Checkout();
		Double gst = 0.18;
		Double gstAmount = cart.getUserCartItem().getCartPrice()*gst;
		Double totalAmount = cart.getUserCartItem().getCartPrice() + gstAmount;
		Integer totalQuantity = cart.getUserCartItem().getTotalCartItem();
		
		checkout.setUserId(cart.getUser().getId());
		checkout.setCart(cart);
		checkout.setGstPercent(gst*100);
		checkout.setGstAmount(gstAmount);
		checkout.setOriginalAmount(cart.getUserCartItem().getCartPrice());
		checkout.setTotalAmount(totalAmount);
		checkout.setItemQuantity(totalQuantity);
		
		return checkoutRepository.save(checkout);
		
	}


	@Override
	public String deleteCheckout(Integer userId) {
		List<OrderPlaced> orderPlaced = orderPlacedRepository.findByUserId(userId);
		Checkout checkout = checkoutRepository.findByUserId(userId)
			.orElseThrow(()-> new NoSuchElementException("Checkout with userId :" + userId + " is not available"));
		
		orderPlaced.forEach(item->item.setCheckout(null));
		orderPlacedRepository.saveAll(orderPlaced);
		checkoutRepository.delete(checkout);
		return "Checkout with cart id : "+ userId+" deleted";
	}

	
}
