package com.devsling.carSalesGarage;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.enums.FuelType;
import com.devsling.carSalesGarage.enums.TransmissionType;
import com.devsling.carSalesGarage.services.CarService;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CarSalesGarageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarSalesGarageApplication.class, args);
	}

    @Bean
	CommandLineRunner start(CarService carService){
		
		return args ->{
			carService.saveCar(new CarRequestDto(1L,"Toyota","Camry",LocalDate.of(2022, 5, 15),25000.0,FuelType.DIESEL,15000,TransmissionType.AUTOMATIC,"car1_picture.jpg"));
			carService.saveCar(new CarRequestDto(2L,"Honda","Civic",LocalDate.of(2020, 9, 10), 18500.0,FuelType.HYBRID, 20500,TransmissionType.MANUAL,"car2_picture.jpg"));
			carService.saveCar(new CarRequestDto(3L,"Ford","Mustang",LocalDate.of(2021, 12, 1),32000.0,FuelType.ELECTRIC,10000,TransmissionType.AUTOMATIC,"car3_picture.jpg"));
			carService.saveCar(new CarRequestDto(4L,"BMW","X5",LocalDate.of(2019, 6, 20),45000.0,FuelType.DIESEL,30000,TransmissionType.AUTOMATIC,"car4_picture.jpg"));
			carService.saveCar(new CarRequestDto(5L,"Mercedes-Benz","C-Class",LocalDate.of(2020, 3, 5),38500.0,FuelType.HYBRID,25000,TransmissionType.AUTOMATIC,"car5_picture.jpg"));
			carService.saveCar(new CarRequestDto(6L,"Nissan","Altima",LocalDate.of(2021, 8, 14),22000.0,FuelType.ELECTRIC,17500,TransmissionType.SEMI_AUTOMATIC,"car6_picture.jpg"));
			carService.saveCar(new CarRequestDto(7L,"Chevrolet","Silverado",LocalDate.of(2018, 11, 30),28500.0,FuelType.DIESEL,40000,TransmissionType.MANUAL,"car7_picture.jpg"));
			carService.saveCar(new CarRequestDto(8L,"Volkswagen","Golf",LocalDate.of(2019, 4, 22), 21000.0,FuelType.HYBRID,22000,TransmissionType.MANUAL,"car8_picture.jpg"));
			carService.saveCar(new CarRequestDto(9L,"Tesla", "Model 3",LocalDate.of(2021, 10, 2),45000.0,FuelType.ELECTRIC,12000,TransmissionType.AUTOMATIC,"car9_picture.jpg"));
			carService.saveCar(new CarRequestDto(10L,"Subaru","Outback",LocalDate.of(2020, 7, 17),26500.0,FuelType.HYBRID, 18000,TransmissionType.SEMI_AUTOMATIC,"car10_picture.jpg"));
		};
	}
}
