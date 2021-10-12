package com.example.reCapProject.entities.request.rental;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.reCapProject.entities.dtos.AdditionalServiceDto;
import com.example.reCapProject.entities.dtos.CreditCardDto;
import com.example.reCapProject.entities.request.get.CreatePosRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateRentalRequest {

	@JsonIgnore
	private String pickUpCity;

	private String deliveryCity;

	private Date rentDate;

	private Date returnDate;

	@JsonIgnore
	private double amount;

	private int carId;
	private int customerId;

	private boolean saveCreditCard;
	private CreditCardDto creditCardDto;

	private List<AdditionalServiceDto> additionalServiceDtos;

	@Valid
	@JsonIgnore
	private CreatePosRequest createPosRequest;

}
