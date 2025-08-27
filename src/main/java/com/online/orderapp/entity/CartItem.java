package com.online.orderapp.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@OneToOne(mappedBy = "cartItem")
	@JsonIgnore
	private Cart cart;
	
	@OneToMany(mappedBy = "cartItem" , cascade = CascadeType.ALL)
	private List<CartRestaurant> cartRestaurant = new ArrayList<>();
	
	private Double cartPrice;
	
}
