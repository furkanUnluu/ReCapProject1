package com.example.reCapProject.core.services;


import org.springframework.stereotype.Service;

import com.example.reCapProject.business.abstracts.CreditCardValidatorService;
import com.example.reCapProject.entities.request.get.CreatePosRequest;
@Service
public class PosServiceAdapter implements CreditCardValidatorService {
	
	PosService posService = new PosService();

	@Override
	public boolean isValid(CreatePosRequest createPosRequest) {
		
		return posService.posServiceVerification(createPosRequest.getCvc(), 
				createPosRequest.getCardNumber(),
				createPosRequest.getPullDate(),
				createPosRequest.getPrice());
	}

    

}

