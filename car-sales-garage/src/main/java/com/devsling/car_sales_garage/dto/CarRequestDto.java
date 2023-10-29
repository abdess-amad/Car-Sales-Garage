package com.devsling.car_sales_garage.dto;

import java.time.LocalDate;

import com.devsling.car_sales_garage.enums.FuelType;
import com.devsling.car_sales_garage.enums.TransmissionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CarRequestDto {
	private Long id;
	private String make;
	private String model;
	private LocalDate registrationDate;
	private double price;
	private FuelType fuelType;
	private int mileage;
	private TransmissionType transmission;
	private String picture;

}
