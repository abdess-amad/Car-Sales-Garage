package com.devsling.car_sales_garage.services;

import java.util.List;
import java.util.Set;

import com.devsling.car_sales_garage.dto.CarRequestDto;
import com.devsling.car_sales_garage.dto.CarResponseDto;
import com.devsling.car_sales_garage.enums.FuelType;

public interface CarService {
	CarResponseDto saveCar(CarRequestDto carRequestDto);

	public List<CarResponseDto> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice);

	Set<String> getAvailableMakes();

	CarResponseDto updateCarPicture(Long carId, String newPicture);

}
