package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CarRepairsDto;
import com.example.reCapProject.entities.request.carRepair.CreateCarRepairRequest;
import com.example.reCapProject.entities.request.carRepair.DeleteCarRepairRequest;
import com.example.reCapProject.entities.request.carRepair.UpdateCarRepairRequest;


	
	public interface CarRepairService {
	
	DataResult<CarRepairsDto> getById(int carRepairId);
	
	DataResult<List<CarRepairsDto>> getAll();
	
	Result add(CreateCarRepairRequest createCarRepairRequest);
	
	Result update(UpdateCarRepairRequest updateCarRepairRequest);

	Result delete(DeleteCarRepairRequest deleteCarRepairRequest);
	

}
