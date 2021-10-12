package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CreditCardDto;
import com.example.reCapProject.entities.request.creditCard.CreateCreditCardRequest;
import com.example.reCapProject.entities.request.creditCard.DeleteCreditCardRequest;
import com.example.reCapProject.entities.request.creditCard.UpdateCreditCardRequest;



public interface CreditCardService {
	

	DataResult<List<CreditCardDto>> getAll();
	
	DataResult<List<CreditCardDto>> getCreditCardByCustomerId(int userId);
	

	Result add(CreateCreditCardRequest createCreditCardRequest);

	Result delete(DeleteCreditCardRequest deleteCreditCardRequest);
	
	Result update(UpdateCreditCardRequest updateCreditCardRequest);
	

	

	

	
}
