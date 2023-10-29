package com.devsling.car_sales_garage.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsling.car_sales_garage.entities.Car;
import com.devsling.car_sales_garage.enums.FuelType;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	public List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double maxPrice);

}
