package com.devsling.carSalesGarage.services;

import java.util.List;
import java.util.Set;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.dto.CarResponseDto;
import com.devsling.carSalesGarage.enums.FuelType;

public interface CarService {
	CarResponseDto saveCar(CarRequestDto carRequestDto);
	public List<CarResponseDto> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice);
	Set<String> getAvailableMakes();
	CarResponseDto updateCarPicture(Long carId, String newPicture);

}
