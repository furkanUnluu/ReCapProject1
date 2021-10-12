package com.example.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reCapProject.business.abstracts.CarDamageService;
import com.example.reCapProject.business.constants.Messages;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.core.utilities.result.SuccessDataResult;
import com.example.reCapProject.core.utilities.result.SuccessResult;
import com.example.reCapProject.dataAccess.abstracts.CarDamageDao;
import com.example.reCapProject.dataAccess.abstracts.CarDao;
import com.example.reCapProject.entities.concretes.Car;
import com.example.reCapProject.entities.concretes.CarDamage;
import com.example.reCapProject.entities.dtos.CarDamageDto;
import com.example.reCapProject.entities.request.carDamage.CreateCarDamageRequest;
import com.example.reCapProject.entities.request.carDamage.DeleteCarDamagesRequest;
import com.example.reCapProject.entities.request.carDamage.UpdateCarDamageRequest;

@Service
public class CarDamageManager implements CarDamageService {

	private CarDamageDao carDamageDao;
	private CarDao carDao;
	private ModelMapper modelMapper;

	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, CarDao carDao, ModelMapper modelMapper) {
		super();
		this.carDamageDao = carDamageDao;
		this.carDao = carDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<CarDamageDto>> getAll() {
		List<CarDamage> carDamages = this.carDamageDao.findAll();
		List<CarDamageDto> carDamageDtos = carDamages.stream()
				.map(carDamage -> modelMapper.map(carDamage, CarDamageDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarDamageDto>>(carDamageDtos, Messages.CARDAMAGESLIST);

	}

	@Override
	public DataResult<CarDamageDto> getById(int carDamagesId) {

		CarDamage carDamage = this.carDamageDao.getById(carDamagesId);
		CarDamageDto carDamageDtos = modelMapper.map(carDamage, CarDamageDto.class);

		return new SuccessDataResult<CarDamageDto>(carDamageDtos, Messages.CARDAMAGELIST);

	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		Car car = this.carDao.getById(createCarDamageRequest.getCarId());

		CarDamage carDamage = new CarDamage();
		carDamage.setDamageDescription(createCarDamageRequest.getDamageDescription());

		carDamage.setCar(car);
		this.carDamageDao.save(carDamage);

		return new SuccessResult(Messages.CARDAMAGEADD);

	}

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		Car car = this.carDao.getById(updateCarDamageRequest.getCarId());

		CarDamage carDamage = new CarDamage();
		carDamage.setDamageDescription(updateCarDamageRequest.getDamageDescription());

		carDamage.setCar(car);
		this.carDamageDao.save(carDamage);

		return new SuccessResult(Messages.CARDAMAGEUPDATE);
	}

	@Override
	public Result delete(DeleteCarDamagesRequest deleteCarDamagesRequest) {
		CarDamage carDamage = this.carDamageDao.getById(deleteCarDamagesRequest.getCarDamagesId());

		this.carDamageDao.delete(carDamage);

		return new SuccessResult(Messages.CARDAMAGEDELETE);

	}

}
