package com.devsling.carSalesGarage.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.devsling.carSalesGarage.repositories.CarRepository;

@SpringBootTest
public class CarServiceImpTest {

	@Autowired
	private CarServiceImp carServiceImp;
	@MockBean
	private CarRepository carRepository;
	
	
}
