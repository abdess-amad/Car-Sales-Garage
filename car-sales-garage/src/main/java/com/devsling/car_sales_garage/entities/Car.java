package com.devsling.car_sales_garage.entities;

import java.time.LocalDate;

import com.devsling.car_sales_garage.enums.FuelType;
import com.devsling.car_sales_garage.enums.TransmissionType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String make;
	private String model;
	private LocalDate registrationDate;
	private double price;
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
	private int mileage;
	@Enumerated(EnumType.STRING)
	private TransmissionType transmission;
	private String picture;

}
