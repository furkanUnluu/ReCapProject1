package com.example.reCapProject.business.abstracts;


import com.example.reCapProject.entities.request.get.CreatePosRequest;
public interface CreditCardValidatorService {
	
	boolean isValid(CreatePosRequest createPosRequest);
	
	
}
