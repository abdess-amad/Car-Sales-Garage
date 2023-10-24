package com.devsling.carSalesGarage.web;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.dto.CarResponseDto;
import com.devsling.carSalesGarage.enums.FuelType;
import com.devsling.carSalesGarage.services.CarService;


@RestController
@RequestMapping(path = "/api")
public class CarRestApi {

	private CarService carService;
	
	public CarRestApi(CarService carService) {
		
		this.carService = carService;
	}
	
	@PostMapping(path = "/Cars")
	public CarResponseDto saveCar(@RequestBody CarRequestDto carRequestDto) {
		
		return carService.saveCar(carRequestDto);
	}
	
	@GetMapping(path = "/CarsByFuelAndPrice")
	
	public List<CarResponseDto> findByFuelTypeAndPriceLessThanEqual(
			@RequestParam FuelType fuelType,@RequestParam double maxPrice) {
		
		return carService.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);
	}
	
	@GetMapping(path = "/Makes")
	public Set<String> getAvailableMakes() {
		
		return carService.getAvailableMakes();
	}
	
	@PutMapping(path = "/cars/{carId}/updatePicture")
	public CarResponseDto updateCarPicture(@PathVariable Long carId,@RequestParam String newPicture) {
		return carService.updateCarPicture(carId, newPicture);
	}
}
