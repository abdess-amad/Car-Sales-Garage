package com.devsling.car_sales_garage.mappers;

import org.mapstruct.Mapper;

import com.devsling.car_sales_garage.dto.CarRequestDto;
import com.devsling.car_sales_garage.dto.CarResponseDto;
import com.devsling.car_sales_garage.entities.Car;

@Mapper(componentModel = "spring")
public interface CarMapper {

	CarResponseDto carToCarResponseDto(Car car);

	Car carRequestDtoToCar(CarRequestDto carRequestDto);
}
