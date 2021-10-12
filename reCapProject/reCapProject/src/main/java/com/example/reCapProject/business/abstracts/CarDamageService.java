package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CarDamageDto;
import com.example.reCapProject.entities.request.carDamage.CreateCarDamageRequest;
import com.example.reCapProject.entities.request.carDamage.DeleteCarDamagesRequest;
import com.example.reCapProject.entities.request.carDamage.UpdateCarDamageRequest;

public interface CarDamageService {
	
	DataResult<List<CarDamageDto>> getAll();
	
	DataResult<CarDamageDto> getById(int carDamagesId);
	
	
	Result add(CreateCarDamageRequest createCarDamageRequest );
	
	Result update(UpdateCarDamageRequest updateCarDamageRequest);
	
	Result delete(DeleteCarDamagesRequest deleteCarDamagesRequest);
	
	

}
