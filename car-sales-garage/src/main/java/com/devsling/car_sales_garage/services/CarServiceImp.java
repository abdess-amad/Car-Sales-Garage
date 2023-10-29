package com.devsling.car_sales_garage.services;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsling.car_sales_garage.dto.CarRequestDto;
import com.devsling.car_sales_garage.dto.CarResponseDto;
import com.devsling.car_sales_garage.entities.Car;
import com.devsling.car_sales_garage.enums.FuelType;
import com.devsling.car_sales_garage.mappers.CarMapper;
import com.devsling.car_sales_garage.repositories.CarRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarServiceImp implements CarService {

	private CarRepository carRepository;
	private CarMapper carMapper;

	public CarServiceImp(CarRepository carRepository, CarMapper carMapper) {

		this.carRepository = carRepository;
		this.carMapper = carMapper;
	}

	@Override
	public CarResponseDto saveCar(CarRequestDto carRequestDto) {

		LocalDate minimumRegistrationDate = LocalDate.of(2015, 1, 1);
		if (carRequestDto.getRegistrationDate().isAfter(minimumRegistrationDate)
				|| carRequestDto.getRegistrationDate().isEqual(minimumRegistrationDate)) {

			Car car = carMapper.carRequestDtoToCar(carRequestDto);
			Car saveCar = carRepository.save(car);

			return carMapper.carToCarResponseDto(saveCar);

		} else {
			throw new IllegalArgumentException("La date d'immatriculation de la voiture doit être en 2015 ou après.");
		}
	}

	@Override
	public List<CarResponseDto> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice) {

		List<Car> cars = carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);

		return cars.stream().map(c -> carMapper.carToCarResponseDto(c)).toList();
	}

	@Override
	public Set<String> getAvailableMakes() {

		List<Car> cars = carRepository.findAll();
		List<CarResponseDto> listCarResponseDto = cars.stream().map(c -> carMapper.carToCarResponseDto(c)).toList();

		return listCarResponseDto.stream().map(CarResponseDto::getMake).collect(Collectors.toSet());
	}

	@Override
	public CarResponseDto updateCarPicture(Long carId, String newPicture) {

		Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException("Voiture introuvable"));

		car.setPicture(newPicture);
		Car carUpdate = carRepository.save(car);

		return carMapper.carToCarResponseDto(carUpdate);
	}

}
