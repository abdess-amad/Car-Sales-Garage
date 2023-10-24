package com.devsling.carSalesGarage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsling.carSalesGarage.entities.Car;
import com.devsling.carSalesGarage.enums.FuelType;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	public List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice);

}
