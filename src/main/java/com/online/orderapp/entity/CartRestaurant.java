package com.online.orderapp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartRestaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "cart_item_id") 
	private CartItem cartItems; //FK will be here
	
	@ManyToOne
	private Food food;
	
//	private Integer restaurantId;
	@ManyToOne
	private Restaurant restaurant;
	
	private Integer quantity;
	
	private Double quantityPrice;
	
}
