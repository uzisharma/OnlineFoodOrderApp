package com.online.orderapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //give getter,setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@NotBlank
	private String restaurantName;
	
	private String address;
	@NotNull
	@NotBlank
	private Long contactNumber;
	@Pattern(
			regexp = "^[A-Za-z0-9]+"
			)
	private String email;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToMany
	@JoinTable(name="restaurant_food", joinColumns = @JoinColumn(name="id_restaurant"), 
				inverseJoinColumns = @JoinColumn(name="id_food")) //its optional and its use to change the auto generated table name
	private List<Food> food;
	
	@OneToMany(mappedBy = "restaurant")
	private List<Order> orders;
	
}
