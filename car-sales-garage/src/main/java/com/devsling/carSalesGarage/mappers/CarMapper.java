package com.devsling.carSalesGarage.mappers;

import org.mapstruct.Mapper;

import com.devsling.carSalesGarage.dto.CarRequestDto;
import com.devsling.carSalesGarage.dto.CarResponseDto;
import com.devsling.carSalesGarage.entities.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {

	CarResponseDto carToCarResponseDto(Car car);
	Car carRequestDtoToCar(CarRequestDto carRequestDto);
}
