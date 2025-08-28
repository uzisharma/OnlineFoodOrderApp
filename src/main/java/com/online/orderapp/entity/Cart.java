package com.online.orderapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	

	@OneToOne
	@JoinColumn(name="user_id")
	private User user; //Owner FK will be stored here
	
	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private CartItem userCartItem;
	
	

}
