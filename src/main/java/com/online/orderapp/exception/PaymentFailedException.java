package com.online.orderapp.exception;

public class PaymentFailedException extends RuntimeException {
	
	public PaymentFailedException(String message) {
		super(message);
	}
}
