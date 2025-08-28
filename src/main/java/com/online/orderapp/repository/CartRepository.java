package com.online.orderapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.online.orderapp.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {


}
