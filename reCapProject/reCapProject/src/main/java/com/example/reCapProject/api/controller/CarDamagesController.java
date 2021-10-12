package com.example.reCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reCapProject.business.abstracts.CarDamageService;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CarDamageDto;
import com.example.reCapProject.entities.request.carDamage.CreateCarDamageRequest;
import com.example.reCapProject.entities.request.carDamage.DeleteCarDamagesRequest;
import com.example.reCapProject.entities.request.carDamage.UpdateCarDamageRequest;

@RestController
@RequestMapping("api/car_damages")
public class CarDamagesController {
	
	CarDamageService carDamageService;
	
	@Autowired
	public CarDamagesController(CarDamageService carDamageService) {
		super();
		this.carDamageService = carDamageService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<CarDamageDto>> getAll() {
		return this.carDamageService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<CarDamageDto>getById(int carDamagesId) {
		return this.carDamageService.getById(carDamagesId);
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCarDamageRequest createCarDamageRequest) {
		return this.carDamageService.add(createCarDamageRequest);
	}

	@PostMapping("update")
	public Result update(@Valid @RequestBody UpdateCarDamageRequest updateCarDamageRequest) {
		return this.carDamageService.update(updateCarDamageRequest);
	}

	@PutMapping("delete")
	public Result delete(@Valid @RequestBody DeleteCarDamagesRequest deleteCarDamageRequest) {
		return this.carDamageService.delete(deleteCarDamageRequest);
	}
	
	
	
	

}
