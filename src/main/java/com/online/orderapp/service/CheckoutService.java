package com.online.orderapp.service;

import com.online.orderapp.dto.checkoutDto.CheckoutRequestDto;
import com.online.orderapp.entity.Checkout;

public interface CheckoutService {

	Checkout addToCheckout(CheckoutRequestDto checkoutRequestDto);

	String deleteCheckout(Integer cartId);

}
