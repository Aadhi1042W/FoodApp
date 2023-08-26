package edu.qsp.food_app.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class FoodOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	private Status foodStatus;
	@CreationTimestamp
	private LocalDateTime orderTime;
	private LocalDateTime deliveredTime;
	private double totalPrice;
	@OneToMany
	private List<FoodProduct> products;
}
