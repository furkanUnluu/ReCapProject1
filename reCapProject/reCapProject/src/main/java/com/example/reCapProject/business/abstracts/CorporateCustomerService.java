package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CorporateCustomerDto;
import com.example.reCapProject.entities.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.example.reCapProject.entities.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.example.reCapProject.entities.request.corporateCustomer.UpdateCorporateCustomerRequest;

public interface CorporateCustomerService {

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

	DataResult<List<CorporateCustomerDto>> getAll();
}
