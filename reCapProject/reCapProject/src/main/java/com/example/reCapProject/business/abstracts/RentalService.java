package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.RentalDto;
import com.example.reCapProject.entities.request.rental.CreateRentalRequest;
import com.example.reCapProject.entities.request.rental.UpdateRentalRequest;

public interface RentalService {

	DataResult<List<RentalDto>> getAll();

	DataResult<RentalDto> getById(int rentalId);

	Result addCorporateCustomer(CreateRentalRequest createRentalRequest);

	Result addIndividualCustomer(CreateRentalRequest createRentalRequest);

	Result updateCorporateCustomer(UpdateRentalRequest updateRentalRequest);

	Result updateIndividualCustomer(UpdateRentalRequest updateRentalRequest);

}
