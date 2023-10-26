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

	        // Create a sample CarResponseDto that you expect as the result
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
	    
}
