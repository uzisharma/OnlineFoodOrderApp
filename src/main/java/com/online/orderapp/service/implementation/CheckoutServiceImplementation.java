package com.online.orderapp.service.implementation;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.online.orderapp.dto.checkoutDto.CheckoutRequestDto;
import com.online.orderapp.entity.Cart;
import com.online.orderapp.entity.Checkout;
import com.online.orderapp.repository.CartRepository;
import com.online.orderapp.repository.CheckoutRepository;
import com.online.orderapp.service.CheckoutService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckoutServiceImplementation implements CheckoutService{
	
	private final CheckoutRepository checkoutRepository;
	private final CartRepository cartRepository;
	
	
	@Override
	public Checkout addToCheckout(CheckoutRequestDto checkoutRequestDto) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(checkoutRequestDto.getCartId())
				.orElseThrow(()-> new NoSuchElementException("cart is not available with the id :"+checkoutRequestDto.getCartId()));
		
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
	public String deleteCheckout(Integer cartId) {
		// TODO Auto-generated method stub
		Checkout checkout = checkoutRepository.findByCartId(cartId)
			.orElseThrow(()-> new NoSuchElementException("Checkout with cart id : "+ cartId+" is not available"));
		
		checkoutRepository.delete(checkout);
		return "Checkout with cart id : "+ cartId+" deleted";
	}

	
}
