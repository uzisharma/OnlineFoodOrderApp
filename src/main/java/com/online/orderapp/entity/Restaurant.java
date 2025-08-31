package com.online.orderapp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //give getter,setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@NotBlank
	private String restaurantName;
	
	private String address;
	
	
	@NotNull
	private Long contactNumber;
	
	private String email;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
	@Column(precision = 2, scale =1)
	private BigDecimal rating;
	
	
	private Integer deliveryTime;
	
	@Column(precision = 6, scale = 2)
	private BigDecimal deliveryCharges;
	
	private byte[] restaurantImage;
	
	
	
	@ManyToMany
	@JoinTable(name="restaurant_food", joinColumns = @JoinColumn(name="id_restaurant"), 
				inverseJoinColumns = @JoinColumn(name="id_food")) //its optional and its use to change the auto generated table name
	private List<Food> food;
	

	
	@JsonIgnore
	@OneToMany(mappedBy="restaurant", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CartItem> cartItem = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<OrderPlaced> orderPlaced;
	
}
