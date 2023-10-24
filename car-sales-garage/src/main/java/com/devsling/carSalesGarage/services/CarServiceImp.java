package com.devsling.carSalesGarage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.dto.CarResponseDto;
import com.devsling.carSalesGarage.entities.Car;
import com.devsling.carSalesGarage.enums.FuelType;
import com.devsling.carSalesGarage.mappers.CarMapper;
import com.devsling.carSalesGarage.repositories.CarRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarServiceImp implements CarService {
	
	private CarRepository carRepository ;
	private CarMapper carMapper;
	
	public CarServiceImp(CarRepository carRepository, CarMapper carMapper){
		
		this.carRepository = carRepository;
		this.carMapper = carMapper;
	}

	@Override
	public CarResponseDto saveCar(CarRequestDto carRequestDto) {
		
        LocalDate minimumRegistrationDate = LocalDate.of(2015, 1, 1);
        if (carRequestDto.getRegistrationDate().isAfter(minimumRegistrationDate) ||
        	carRequestDto.getRegistrationDate().isEqual(minimumRegistrationDate)) {
		
		Car car=carMapper.carRequestDtoToCar(carRequestDto);
		Car saveCar = carRepository.save(car);
		return carMapper.carToCarResponseDto(saveCar);
		
        } else {
            throw new IllegalArgumentException("La date d'immatriculation de la voiture doit être en 2015 ou après.");
        }
	}

	@Override
	public List<CarResponseDto> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice) {
		
		List<Car> cars =carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);
		List<CarResponseDto> ListCarResponseDto=cars.stream()
				.map(c ->  carMapper.carToCarResponseDto(c))
				.collect(Collectors.toList());
		
		
		return ListCarResponseDto;
	}

	@Override
	public Set<String> getAvailableMakes() {
		
		List<Car> cars = carRepository.findAll();
		List<CarResponseDto> ListCarResponseDto=cars.stream()
				.map(c ->  carMapper.carToCarResponseDto(c))
				.collect(Collectors.toList());
		
		return ListCarResponseDto.stream().map(CarResponseDto::getMake).collect(Collectors.toSet());
	}

	@Override
	public CarResponseDto updateCarPicture(Long carId, String newPicture) {
		
		 Car car = carRepository.findById(carId)
	                .orElseThrow(() -> new NoSuchElementException("Voiture introuvable"));

	     car.setPicture(newPicture);
	     Car carUpdate= carRepository.save(car);
	        
	     return carMapper.carToCarResponseDto(carUpdate);
	}

}
