package com.example.reCapProject.business.abstracts;

import java.util.List;

import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.concretes.Color;
import com.example.reCapProject.entities.dtos.ColorDto;
import com.example.reCapProject.entities.request.color.CreateColorRequest;
import com.example.reCapProject.entities.request.color.DeleteColorRequest;
import com.example.reCapProject.entities.request.color.UpdateColorRequest;

public interface ColorService {
	
	DataResult<List<ColorDto>> getAll();
	
	DataResult<ColorDto> getById(int colorId);
	
	DataResult<List<Color>> getByCarId(int carId);
	
	Result add(CreateColorRequest createColorRequest);
	
	Result update(UpdateColorRequest updateColorRequest);
	
	Result delete(DeleteColorRequest deleteColorRequest);

}
 