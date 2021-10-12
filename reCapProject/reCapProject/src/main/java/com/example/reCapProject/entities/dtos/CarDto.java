package com.example.reCapProject.entities.dtos;

import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

	private int carId;
	private String carName;
	private int modelYear;
	private String description;
	private double dailyPrice;
	private String city;

}
