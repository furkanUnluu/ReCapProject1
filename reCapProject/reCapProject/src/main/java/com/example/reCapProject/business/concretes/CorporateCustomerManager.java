package com.example.reCapProject.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reCapProject.business.abstracts.CorporateCustomerService;
import com.example.reCapProject.business.constants.Messages;
import com.example.reCapProject.core.utilities.business.BusinessRules;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.ErrorResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.core.utilities.result.SuccessDataResult;
import com.example.reCapProject.core.utilities.result.SuccessResult;
import com.example.reCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.example.reCapProject.dataAccess.abstracts.CustomerDao;
import com.example.reCapProject.entities.concretes.CorporateCustomer;
import com.example.reCapProject.entities.dtos.CorporateCustomerDto;
import com.example.reCapProject.entities.request.corporateCustomer.CreateCorporateCustomerRequest;
import com.example.reCapProject.entities.request.corporateCustomer.DeleteCorporateCustomerRequest;
import com.example.reCapProject.entities.request.corporateCustomer.UpdateCorporateCustomerRequest;
@Service
public class CorporateCustomerManager implements CorporateCustomerService {
	
	private CustomerDao customerDao;
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapper modelMapper;
	
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao,CustomerDao customerDao,ModelMapper modelMapper) {
		super();
		this.corporateCustomerDao = corporateCustomerDao;
		this.customerDao=customerDao;
		this.modelMapper=modelMapper;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) {
		var result = BusinessRules.run(checkEmail(createCorporateCustomerRequest.getEmail()));
		
		if (result != null) {
			return result;
		}
		CorporateCustomer corporateCustomer = this.modelMapper.map(createCorporateCustomerRequest, CorporateCustomer.class);

	
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERADD);
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		//MAPLEME İŞLEMİNDE BİR ÖNCEKİ KAYIT GÜNCELLEŞTİRİLMEMEKTEDİR.
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(updateCorporateCustomerRequest.getId());
		

		corporateCustomer.setCompanyName(updateCorporateCustomerRequest.getCompanyName());
		corporateCustomer.setEmail(updateCorporateCustomerRequest.getEmail());
		corporateCustomer.setPassword(updateCorporateCustomerRequest.getPassword());
		corporateCustomer.setTaxNumber(updateCorporateCustomerRequest.getTaxNumber());
		
		this.corporateCustomerDao.save(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERUPDATE);
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(deleteCorporateCustomerRequest.getId());

		this.corporateCustomerDao.delete(corporateCustomer);
		return new SuccessResult(Messages.CUSTOMERDELETE);
	}

	@Override
	public DataResult<List<CorporateCustomerDto>> getAll() {
		List<CorporateCustomer> corporateCustomers= this.corporateCustomerDao.findAll();
		List<CorporateCustomerDto> corporateCustomerDtos=corporateCustomers.stream().map(corporateCustomer -> modelMapper.map(corporateCustomer, CorporateCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CorporateCustomerDto>>(corporateCustomerDtos,Messages.CORPORATECUSTOMERSLIST);
	}
	
	
	public Result checkEmail(String email) {

		if (this.customerDao.existsCustomerByEmail(email)) {
			return new ErrorResult(Messages.EMAILERROR);
		}
		return new SuccessResult();
	}
}
