package com.example.reCapProject.business.abstracts;

import java.io.IOException;
import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CarImageDto;
import com.example.reCapProject.entities.request.carImage.CreateCarImageRequest;
import com.example.reCapProject.entities.request.carImage.DeleteCarImageRequest;
import com.example.reCapProject.entities.request.carImage.UpdateCarImageRequest;

public interface CarImageService {

	DataResult<List<CarImageDto>> getAll();
	
	DataResult <List<CarImageDto>>getImagesWıthCarId (int carId);
	

	Result add(CreateCarImageRequest createCarImageRequest)throws IOException;

	Result update(UpdateCarImageRequest updateCarImageRequest)throws IOException;

	Result delete(DeleteCarImageRequest deleteCarImageRequest);

	

	

}
