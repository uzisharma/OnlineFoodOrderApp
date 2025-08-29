package com.online.orderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer>{

}
