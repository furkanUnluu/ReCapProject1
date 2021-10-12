package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.concretes.Car;
import com.example.reCapProject.entities.dtos.CarDetailDto;
import com.example.reCapProject.entities.dtos.CarDto;
import com.example.reCapProject.entities.request.car.CreateCarRequest;
import com.example.reCapProject.entities.request.car.DeleteCarRequest;
import com.example.reCapProject.entities.request.car.UpdateCarRequest;

public interface CarService {

	DataResult<List<CarDto>> getAll();

	DataResult<CarDto> getById(int carId);

	DataResult<List<CarDetailDto>> getCarWithDetails();

	DataResult<List<Car>> getByBrandId(int brandId);

	DataResult<List<Car>> getByColorId(int colorId);

	DataResult<List<Car>> getAvailableCars();

	DataResult<List<Car>> getByCity(String city);

	Result add(CreateCarRequest createCarRequest);

	Result update(UpdateCarRequest updateCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);

}
