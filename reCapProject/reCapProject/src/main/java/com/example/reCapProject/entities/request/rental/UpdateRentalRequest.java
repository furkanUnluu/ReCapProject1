package com.example.reCapProject.entities.request.rental;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.reCapProject.entities.dtos.AdditionalServiceDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {

	private String pickUpCity;

	private String deliveryCity;

	private String endMileage;

	@NotNull
	private int carId;
	private int customerId;
	private int rentalId;
	
	private Date rentDate;
	private Date returnDate;

	
	private List<AdditionalServiceDto> additionalServiceDtos;

	private boolean returnStatus;

}
