package com.devsling.carSalesGarage.dto;

import java.time.LocalDate;
import com.devsling.carSalesGarage.enums.FuelType;
import com.devsling.carSalesGarage.enums.TransmissionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CarResponseDto {
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
