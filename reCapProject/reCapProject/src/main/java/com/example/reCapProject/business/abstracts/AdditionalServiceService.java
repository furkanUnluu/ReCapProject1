package com.example.reCapProject.business.abstracts;
import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.AdditionalServiceDetailDto;
import com.example.reCapProject.entities.request.additionalService.CreateAdditionalServiceRequest;
import com.example.reCapProject.entities.request.additionalService.DeleteAdditionalServiceRequest;
import com.example.reCapProject.entities.request.additionalService.UpdateAdditionalServiceRequest;

public interface AdditionalServiceService {
	
	DataResult<AdditionalServiceDetailDto> getById(int additionalId);
	
	DataResult<List<AdditionalServiceDetailDto>> getAll();
    
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);
	
	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);
	
	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest);
	
	DataResult<List<AdditionalServiceDetailDto>> getByRentalId(int rentalId);

}
