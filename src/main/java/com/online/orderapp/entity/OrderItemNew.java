package com.online.orderapp.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class OrderItemNew {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Restaurant restaurant;
	
	@ManyToMany
	private List<Food> food;
	
	private Integer quantity;
	
	private Integer quantityPrice;
	
	
}
