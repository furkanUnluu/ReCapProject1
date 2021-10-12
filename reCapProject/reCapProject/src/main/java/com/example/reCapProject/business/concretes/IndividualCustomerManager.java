package com.example.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reCapProject.business.abstracts.IndividualCustomerService;
import com.example.reCapProject.business.constants.Messages;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.core.utilities.result.SuccessDataResult;
import com.example.reCapProject.core.utilities.result.SuccessResult;
import com.example.reCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.example.reCapProject.entities.concretes.IndividualCustomer;
import com.example.reCapProject.entities.dtos.IndividualCustomerDto;
import com.example.reCapProject.entities.request.individualCustomer.CreateIndividualCustomerRequest;
import com.example.reCapProject.entities.request.individualCustomer.DeleteIndividualCustomerRequest;
import com.example.reCapProject.entities.request.individualCustomer.UpdateIndividualCustomerRequest;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {

	@Autowired
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapper modelMapper;

	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapper modelMapper) {
		super();
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {

		IndividualCustomer individualCustomer = new IndividualCustomer();

		individualCustomer.setFirstName(createIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(createIndividualCustomerRequest.getLastName());
		individualCustomer.setEmail(createIndividualCustomerRequest.getEmail());
		individualCustomer.setPassword(createIndividualCustomerRequest.getPassword());
		individualCustomer.setIdentityNumber(createIndividualCustomerRequest.getIdentityNumber());
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CUSTOMERADD);

	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(updateIndividualCustomerRequest.getId());
			

		individualCustomer.setFirstName(updateIndividualCustomerRequest.getFirstName());
		individualCustomer.setLastName(updateIndividualCustomerRequest.getLastName());
		individualCustomer.setEmail(updateIndividualCustomerRequest.getEmail());
		individualCustomer.setPassword(updateIndividualCustomerRequest.getPassword());
		individualCustomer.setIdentityNumber(updateIndividualCustomerRequest.getIdentityNumber());
		
		this.individualCustomerDao.save(individualCustomer);
		return new SuccessResult(Messages.CUSTOMERUPDATE);
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(deleteIndividualCustomerRequest.getId());

		this.individualCustomerDao.delete(individualCustomer);
		return new SuccessResult(Messages.CUSTOMERDELETE);
	}

	@Override
	public DataResult<List<IndividualCustomerDto>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();
		List<IndividualCustomerDto> individualCustomerDtos = individualCustomers.stream()
				.map(individualCustomer -> modelMapper.map(individualCustomer, IndividualCustomerDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<IndividualCustomerDto>>(individualCustomerDtos);
	}
}
