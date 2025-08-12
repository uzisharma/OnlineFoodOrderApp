package com.online.orderapp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.orderapp.util.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "FoodOrder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	private Double totalPrice;
	

}
