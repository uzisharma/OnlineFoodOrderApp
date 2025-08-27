package com.online.orderapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JsonIgnore
	private CartItem cartItem;
	
	@ManyToOne
	private Food food;
	
//	private Integer restaurantId;
	@ManyToOne
	private Restaurant restaurant;
	
	private Integer quantity;
	
	private Double quantityPrice;
	
}
