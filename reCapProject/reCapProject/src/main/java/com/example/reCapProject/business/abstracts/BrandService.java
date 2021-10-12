package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.BrandDto;
import com.example.reCapProject.entities.request.brand.CreateBrandRequest;
import com.example.reCapProject.entities.request.brand.DeleteBrandRequest;
import com.example.reCapProject.entities.request.brand.UpdateBrandRequest;

public interface BrandService {

	DataResult<List<BrandDto>> getAll();

	DataResult<BrandDto> getById(int brandId);

	Result add(CreateBrandRequest createBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);

}
