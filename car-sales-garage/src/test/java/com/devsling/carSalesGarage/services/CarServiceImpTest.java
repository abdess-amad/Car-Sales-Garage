package com.devsling.carSalesGarage.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.dto.CarResponseDto;
import com.devsling.carSalesGarage.entities.Car;
import com.devsling.carSalesGarage.enums.FuelType;
import com.devsling.carSalesGarage.enums.TransmissionType;
import com.devsling.carSalesGarage.mappers.CarMapper;
import com.devsling.carSalesGarage.repositories.CarRepository;

@SpringBootTest
public class CarServiceImpTest {

	 @InjectMocks
	    private CarServiceImp carService;

	    @Mock
	    private CarMapper carMapper;

	    @Mock
	    private CarRepository carRepository;
	    
	    @Test
	    public void testSaveCar_ValidRegistrationDate() {
	    	// mock du CarMapper pour renvoyer car et du CarRepository pour renvoyer savrdCar
	        CarRequestDto carRequestDto = new CarRequestDto(1L,"Toyota","Camry",LocalDate.of(2022, 5, 15),25000.0,FuelType.DIESEL,15000,TransmissionType.AUTOMATIC,"car1_picture.jpg");
	        CarResponseDto carResponseDto = new CarResponseDto(1L,"Toyota","Camry",LocalDate.of(2022, 5, 15),25000.0,FuelType.DIESEL,15000,TransmissionType.AUTOMATIC,"car1_picture.jpg");
	        Car car = new Car(1L,"Toyota","Camry",LocalDate.of(2022, 5, 15),25000.0,FuelType.DIESEL,15000,TransmissionType.AUTOMATIC,"car1_picture.jpg");

	        Mockito.when(carMapper.carRequestDtoToCar(carRequestDto)).thenReturn(car);
	        Mockito.when(carMapper.carToCarResponseDto(car)).thenReturn(carResponseDto);
	        Mockito.when(carRepository.save(car)).thenReturn(car);

	        // Act
	        CarResponseDto savedCar = carService.saveCar(carRequestDto);
	        
	        // Assert
	        assertNotNull(savedCar);
	        assertEquals(car.getId(), savedCar.getId());
	    }

	    @Test
	    public void testSaveCar_InvalidRegistrationDate() {
	    	
	        CarRequestDto carRequestDto = new CarRequestDto(1L,"Toyota","Camry",LocalDate.of(2012, 5, 15),25000.0,FuelType.DIESEL,15000,TransmissionType.AUTOMATIC,"car1_picture.jpg");

	        assertThrows(IllegalArgumentException.class, () -> carService.saveCar(carRequestDto));
	    }
	    
	    
	    @Test
	    public void testGetAvailableMakes() {
	        List<Car> cars = Arrays.asList(
	            new Car(1L,"Toyota","Camry",LocalDate.of(2022, 5, 15),25000.0,FuelType.DIESEL,15000,TransmissionType.AUTOMATIC,"car1_picture.jpg"),
	            new Car(2L,"Honda","Civic",LocalDate.of(2020, 9, 10), 18500.0,FuelType.HYBRID, 20500,TransmissionType.MANUAL,"car2_picture.jpg"),
	            new Car(3L,"Ford","Mustang",LocalDate.of(2021, 12, 1),32000.0,FuelType.ELECTRIC,10000,TransmissionType.AUTOMATIC,"car3_picture.jpg"),
	            new Car(4L,"Ford","Mustang",LocalDate.of(2021, 12, 1),32000.0,FuelType.ELECTRIC,10000,TransmissionType.AUTOMATIC,"car4_picture.jpg")
	        );

	        // Mock methode repository pour retourner une list des cars
	        Mockito.when(carRepository.findAll()).thenReturn(cars);

	        // Mock  mapper pour  maper Car a CarResponseDto
	        Mockito.when(carMapper.carToCarResponseDto(Mockito.any(Car.class))).thenAnswer(invocation -> {
	            Car car = invocation.getArgument(0);
	            CarResponseDto responseDto = new CarResponseDto();
	            responseDto.setMake(car.getMake());
	           
	            return responseDto;
	        });

	      
	        Set<String> makes = carService.getAvailableMakes();

	        assertNotNull(makes);
	        assertEquals(3, makes.size());
	        assertTrue(makes.contains("Toyota"));
	        assertTrue(makes.contains("Honda"));
	        assertTrue(makes.contains("Ford"));
	    }
	    
	    @Test
	    public void testUpdateCarPicture_CarFound() {
	        Long carId = 1L;
	        String newPicture = "new_picture_url";
	        Car car = new Car();
	        car.setId(carId);
	        car.setPicture(newPicture);
	        CarResponseDto carResponseDto = new CarResponseDto();
	        carResponseDto.setId(carId);
	        carResponseDto.setPicture(newPicture);

	        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.of(car));
	        Mockito.when(carRepository.save(car)).thenReturn(car);
	        Mockito.when(carMapper.carToCarResponseDto(car)).thenReturn(carResponseDto);
	        
	        // Act
	        CarResponseDto updatedCarResponseDto  = carService.updateCarPicture(carId, newPicture);

	        // Assert
	        assertNotNull(updatedCarResponseDto );
	        assertEquals(newPicture, updatedCarResponseDto.getPicture());
	    }

	    @Test
	    public void testUpdateCarPicture_CarNotFound() {
	    	// Arrange
	        Long carId = 1L;
	        String newPicture = "new_picture_url";

	        // Mockez la méthode findById du repository pour renvoyer un Optional vide (voiture non trouvée)
	        Mockito.when(carRepository.findById(carId)).thenReturn(Optional.empty());

	        // Act
	        // Appelez la méthode de service et attendez qu'elle lève une exception
	        assertThrows(NoSuchElementException.class, () -> carService.updateCarPicture(carId, newPicture));
	    }

	    
	}

