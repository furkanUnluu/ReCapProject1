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

import com.example.reCapProject.business.abstracts.BrandService;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.BrandDto;
import com.example.reCapProject.entities.request.brand.CreateBrandRequest;
import com.example.reCapProject.entities.request.brand.DeleteBrandRequest;
import com.example.reCapProject.entities.request.brand.UpdateBrandRequest;

@RestController
@RequestMapping("api/brands")
public class BrandsController {

	BrandService brandService;

	@Autowired
	public BrandsController(BrandService brandService) {
		super();
		this.brandService = brandService;
	}

	@GetMapping("/getall")
	public DataResult<List<BrandDto>> getAll() {
		return this.brandService.getAll();
	}

	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
		return this.brandService.add(createBrandRequest);
	}

	@GetMapping("/getbyid")
	public DataResult<BrandDto> getById(int brandId) {
		return this.brandService.getById(brandId);
	}

	@PostMapping("update")
	public Result update(UpdateBrandRequest updateBrandRequest) {
		return this.brandService.update(updateBrandRequest);
	}

	@PutMapping("delete")
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		return this.brandService.delete(deleteBrandRequest);
	}

}
