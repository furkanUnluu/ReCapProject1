package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.IndividualCustomerDto;
import com.example.reCapProject.entities.request.individualCustomer.CreateIndividualCustomerRequest;
import com.example.reCapProject.entities.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.example.reCapProject.entities.request.individualCustomer.UpdateIndividualCustomerRequest;

public interface IndividualCustomerService {
	
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest);

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest);

	DataResult<List<IndividualCustomerDto>> getAll();
}
