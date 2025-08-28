package com.online.orderapp.entity;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="cart_id") //FK now lives here in cart_item table
	private Cart cart;
	
	
	//Inversion side deleting cart-item will delete associated cartRestaurant
	//parent
	@OneToMany(mappedBy = "cartItems",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CartRestaurant> cartRestaurant = new ArrayList<>();
	
	
	private Double cartPrice;
	
	private Integer totalCartItem;
	
}
