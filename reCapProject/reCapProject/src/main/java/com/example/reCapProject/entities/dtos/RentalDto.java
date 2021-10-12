package com.example.reCapProject.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDto {

	private String pickUpCity;

	private String deliveryCity;

	private Date rentDate;

	private Date returnDate;

	@JsonIgnore
	private double amount;

	private String carName;

	private int carId;
	private int customerId;

}
