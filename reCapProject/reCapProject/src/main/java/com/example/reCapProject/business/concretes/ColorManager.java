package com.example.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reCapProject.business.abstracts.ColorService;
import com.example.reCapProject.business.constants.Messages;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.core.utilities.result.SuccessDataResult;
import com.example.reCapProject.core.utilities.result.SuccessResult;
import com.example.reCapProject.dataAccess.abstracts.ColorDao;
import com.example.reCapProject.entities.concretes.Color;
import com.example.reCapProject.entities.dtos.ColorDto;
import com.example.reCapProject.entities.request.color.CreateColorRequest;
import com.example.reCapProject.entities.request.color.DeleteColorRequest;
import com.example.reCapProject.entities.request.color.UpdateColorRequest;

@Service
public class ColorManager implements ColorService{
	

	private ColorDao colorDao;
	private ModelMapper modelMapper;
	
	@Autowired
	public ColorManager(ColorDao colorDao,ModelMapper modelMapper) {
		super();
		this.colorDao = colorDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) {
		
		Color color= this.modelMapper.map(createColorRequest, Color.class);
		
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLORADD);
		
	}

	@Override
	public DataResult<List<ColorDto>> getAll() {
		List<Color> colors= this.colorDao.findAll();
		List<ColorDto> colorDtos=colors.stream().map(color -> modelMapper.map(color, ColorDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ColorDto>>(colorDtos,Messages.COLORSLIST);
	}

	@Override
	public DataResult<ColorDto> getById(int colorId) {
		
		Color color= this.colorDao.getById(colorId);
		ColorDto colorDtos=modelMapper.map(color, ColorDto.class);
		
		return new SuccessDataResult<ColorDto>(colorDtos,Messages.COLORLIST);
	
	}

	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		Color color = this.modelMapper.map(updateColorRequest, Color.class);
	
		
		this.colorDao.save(color);
		return new SuccessResult(Messages.COLORUPDATE);
		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		Color color = this.colorDao.getById(deleteColorRequest.getColorId());
		
		
		this.colorDao.delete(color);
		return new SuccessResult(Messages.COLORDELETE);
		
	}

	@Override
	public DataResult<List<Color>> getByCarId(int carId) {
		return new SuccessDataResult<List<Color>>
		(this.colorDao.findAll(),Messages.COLORLIST);
	}
	

}
