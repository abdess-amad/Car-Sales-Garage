package com.devsling.carSalesGarage.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.util.HashSet;
import java.util.Set;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.dto.CarResponseDto;
import com.devsling.carSalesGarage.services.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(CarRestApi.class)
public class CarRestApiTest {
	    @MockBean
	    private CarService carService;

	    @Autowired
	    private MockMvc mockMvc;
	    @Autowired
	    private ObjectMapper objectMapper;

	    @Test
	    public void testSaveCar() throws Exception {
	        CarRequestDto requestDto = new CarRequestDto();
	        requestDto.setMake("Toyota");

	        CarResponseDto carResponseDto = new CarResponseDto();
	        carResponseDto.setId(1L);
	        carResponseDto.setMake(requestDto.getMake());

	        // Mock the carService to return the expected response
	        when(carService.saveCar(any(CarRequestDto.class))).thenReturn(carResponseDto);

	        // Perform a POST request to the endpoint
	        mockMvc.perform(post("/api/Cars")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(requestDto)))
	            .andExpect(status().isOk())
	            .andExpect(content().json("{ \"id\": 1, \"make\": \"Toyota\" }"));	
	    }
	    
	    @Test
	    public void testGetAvailableMakes() throws Exception {
	        Set<String> availableMakes = new HashSet<>();
	        availableMakes.add("Toyota");
	        availableMakes.add("Honda");

	        // Mocker  du carService pour renvoyer l'échantillon de marques disponibles
	        when(carService.getAvailableMakes()).thenReturn(availableMakes);

	        mockMvc.perform(get("/api/Makes"))
	            .andExpect(status().isOk())
	            .andExpect(content().json("[\"Toyota\", \"Honda\"]"));
	    }
	    @Test
	    public void testUpdateCarPicture() throws Exception {
	        Long carId = 1L;
	        String newPicture = "new-car-picture.jpg";
	        CarResponseDto expectedResponse = new CarResponseDto();
	        expectedResponse.setId(carId);
	        expectedResponse.setPicture(newPicture);

	        when(carService.updateCarPicture(carId, newPicture)).thenReturn(expectedResponse);

	        mockMvc.perform(put("/api/cars/{carId}/updatePicture", carId)
	            .param("newPicture", newPicture))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.id").value(carId))
	            .andExpect(jsonPath("$.picture").value(newPicture));
	    }

}
