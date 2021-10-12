package com.example.reCapProject.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reCapProject.business.abstracts.AllFindexPointCheckService;
import com.example.reCapProject.business.abstracts.CreditCardService;
import com.example.reCapProject.business.abstracts.CreditCardValidatorService;
import com.example.reCapProject.business.abstracts.RentalService;
import com.example.reCapProject.business.constants.Messages;
import com.example.reCapProject.core.utilities.business.BusinessRules;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.ErrorResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.core.utilities.result.SuccessDataResult;
import com.example.reCapProject.core.utilities.result.SuccessResult;
import com.example.reCapProject.dataAccess.abstracts.AdditionalServiceDao;
import com.example.reCapProject.dataAccess.abstracts.CarDao;
import com.example.reCapProject.dataAccess.abstracts.CarRepairDao;
import com.example.reCapProject.dataAccess.abstracts.CorporateCustomerDao;
import com.example.reCapProject.dataAccess.abstracts.IndividualCustomerDao;
import com.example.reCapProject.dataAccess.abstracts.RentalDao;
import com.example.reCapProject.entities.concretes.AdditionalService;
import com.example.reCapProject.entities.concretes.Car;
import com.example.reCapProject.entities.concretes.CarRepair;
import com.example.reCapProject.entities.concretes.CorporateCustomer;
import com.example.reCapProject.entities.concretes.IndividualCustomer;
import com.example.reCapProject.entities.concretes.Rental;
import com.example.reCapProject.entities.dtos.AdditionalServiceDto;
import com.example.reCapProject.entities.dtos.CreditCardDto;
import com.example.reCapProject.entities.dtos.RentalDetailsDto;
import com.example.reCapProject.entities.dtos.RentalDto;
import com.example.reCapProject.entities.request.creditCard.CreateCreditCardRequest;
import com.example.reCapProject.entities.request.get.CreatePosRequest;
import com.example.reCapProject.entities.request.rental.CreateRentalRequest;
import com.example.reCapProject.entities.request.rental.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarDao carDao;
	private IndividualCustomerDao individualCustomerDao;
	private CorporateCustomerDao corporateCustomerDao;
	private AllFindexPointCheckService allFindexPointCheckService;
	private CarRepairDao carRepairDao;
	private CreditCardService creditCardService;
	private CreditCardValidatorService creditCardValidatorService;
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapper modelMapper;

	@Autowired
	public RentalManager(RentalDao rentalDao, CarDao carDao, IndividualCustomerDao individualCustomerDao,
			CorporateCustomerDao corporateCustomerDao, AllFindexPointCheckService allFindexPointCheckService,
			CarRepairDao carRepairDao, CreditCardService creditCardService,
			CreditCardValidatorService creditCardValidatorService, AdditionalServiceDao additionalServiceDao,
			ModelMapper modelMapper) {

		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.individualCustomerDao = individualCustomerDao;
		this.corporateCustomerDao = corporateCustomerDao;
		this.allFindexPointCheckService = allFindexPointCheckService;
		this.carRepairDao = carRepairDao;
		this.creditCardService = creditCardService;
		this.creditCardValidatorService = creditCardValidatorService;
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public DataResult<List<RentalDto>> getAll() {

		List<Rental> rentals = this.rentalDao.findAll();

		List<RentalDto> rentalDetailDto = rentals.stream().map(rental -> modelMapper.map(rental, RentalDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<RentalDto>>(rentalDetailDto, Messages.RENTALLIST);
	}

	@Override
	public DataResult<RentalDto> getById(int rentalId) {
		Rental rentals = this.rentalDao.getById(rentalId);
		RentalDto rentalDtos = modelMapper.map(rentals, RentalDto.class);

		return new SuccessDataResult<RentalDto>(rentalDtos);
	}

	@Transactional
	@Override
	public Result addCorporateCustomer(CreateRentalRequest createRentalRequest) {

		double amount = calculateTotalAmountofRental(createRentalRequest.getCarId(), createRentalRequest.getRentDate(),
				createRentalRequest.getReturnDate(), createRentalRequest.getDeliveryCity(),
				createRentalRequest.getPickUpCity(), createRentalRequest.getAdditionalServiceDtos());

		var result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId()),
				checkCorporateCustomerFindexPoint(
						this.corporateCustomerDao.getById(createRentalRequest.getCustomerId()),
						this.carDao.getById(createRentalRequest.getCarId())),
				checkIsCarCare(createRentalRequest.getCarId()),
				this.checkPaymentControl(createRentalRequest.getCreditCardDto(), amount));

		if (result != null) {
			return result;
		}

		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(createRentalRequest.getCustomerId());
	
		Car car = this.carDao.getById(createRentalRequest.getCarId());
		Rental rental = new Rental();
		rental.setPickUpCity(car.getCity());
		rental.setDeliveryCity(createRentalRequest.getDeliveryCity());
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setStartingMileage(car.getCurrentMileage());
		rental.setAdditionalServices(convertAdditionalServiceFromDto(createRentalRequest.getAdditionalServiceDtos()));
		rental.setAmount(amount);
		rental.setCar(car);
		rental.setCustomer(corporateCustomer);

		this.rentalDao.save(rental);
		car.setCity(createRentalRequest.getDeliveryCity());
		this.carDao.save(car);

		if (createRentalRequest.isSaveCreditCard()) {
			this.saveCardInformation(createRentalRequest.getCreditCardDto(), createRentalRequest.getCustomerId());
		}

		return new SuccessResult(Messages.RENTALADD);
	}

	@Override
	public Result addIndividualCustomer(CreateRentalRequest createRentalRequest) {

		Rental rental = new Rental();
		Car car = this.carDao.getById(createRentalRequest.getCarId());

		double amount = calculateTotalAmountofRental(createRentalRequest.getCarId(), createRentalRequest.getRentDate(),
				createRentalRequest.getReturnDate(), createRentalRequest.getDeliveryCity(),
				createRentalRequest.getPickUpCity(), createRentalRequest.getAdditionalServiceDtos());

		var result = BusinessRules.run(checkCarIsReturned(createRentalRequest.getCarId()),
				checkIndiviualCustomerFindexPoint(
						this.individualCustomerDao.getById(createRentalRequest.getCustomerId()),
						this.carDao.getById(createRentalRequest.getCarId())),
				checkIsCarCare(createRentalRequest.getCarId()),
				this.checkPaymentControl(createRentalRequest.getCreditCardDto(), amount));

		if (result != null) {
			return result;
		}

		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(createRentalRequest.getCustomerId());

		rental.setPickUpCity(car.getCity());
		rental.setDeliveryCity(createRentalRequest.getDeliveryCity());
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setStartingMileage(car.getCurrentMileage());
		rental.setAdditionalServices(convertAdditionalServiceFromDto(createRentalRequest.getAdditionalServiceDtos()));
		rental.setAmount(amount);
		rental.setCar(car);
		rental.setCustomer(individualCustomer);

		this.rentalDao.save(rental);
		car.setCity(createRentalRequest.getDeliveryCity());
		this.carDao.save(car);

		if (createRentalRequest.isSaveCreditCard()) {
			this.saveCardInformation(createRentalRequest.getCreditCardDto(), createRentalRequest.getCustomerId());
		}

		return new SuccessResult(Messages.RENTALADD);
	}

	@Override
	public Result updateCorporateCustomer(UpdateRentalRequest updateRentalRequest) {
		Car car = this.carDao.getById(updateRentalRequest.getCarId());
		Rental rental = this.rentalDao.getById(updateRentalRequest.getRentalId());

		double amount = calculateTotalAmountofRental(updateRentalRequest.getCarId(), updateRentalRequest.getRentDate(),
				updateRentalRequest.getReturnDate(), updateRentalRequest.getDeliveryCity(),
				updateRentalRequest.getPickUpCity(), updateRentalRequest.getAdditionalServiceDtos());
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setUserId(updateRentalRequest.getCustomerId());

		var result = BusinessRules.run(checkCorporateCustomerFindexPoint(
				this.corporateCustomerDao.getById(updateRentalRequest.getCustomerId()),
				this.carDao.getById(updateRentalRequest.getCarId())));
		if (result != null) {
			return result;
		}

		rental.setPickUpCity(updateRentalRequest.getPickUpCity());
		rental.setDeliveryCity(updateRentalRequest.getDeliveryCity());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setReturnStatus(updateRentalRequest.isReturnStatus());
		rental.setEndMileage(updateRentalRequest.getEndMileage());
		rental.setAdditionalServices(convertAdditionalServiceFromDto(updateRentalRequest.getAdditionalServiceDtos()));
		rental.setAmount(amount);
		rental.setCar(car);
		rental.setCustomer(corporateCustomer);

		this.rentalDao.save(rental);
		car.setCurrentMileage(updateRentalRequest.getEndMileage());
		car.setCity(updateRentalRequest.getDeliveryCity());
		this.carDao.save(car);

		return new SuccessResult(Messages.RENTALUPDATE);
	}

	@Override
	public Result updateIndividualCustomer(UpdateRentalRequest updateRentalRequest) {

		Car car = this.carDao.getById(updateRentalRequest.getCarId());
		Rental rental = this.rentalDao.getById(updateRentalRequest.getRentalId());

		double amount = calculateTotalAmountofRental(updateRentalRequest.getCarId(), updateRentalRequest.getRentDate(),
				updateRentalRequest.getReturnDate(), updateRentalRequest.getDeliveryCity(),
				updateRentalRequest.getPickUpCity(), updateRentalRequest.getAdditionalServiceDtos());

		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(updateRentalRequest.getCustomerId());

		var result = BusinessRules.run(checkIndiviualCustomerFindexPoint(
				this.individualCustomerDao.getById(updateRentalRequest.getCustomerId()),
				this.carDao.getById(updateRentalRequest.getCarId())));
		if (result != null) {
			return result;
		}

		rental.setPickUpCity(updateRentalRequest.getPickUpCity());
		rental.setDeliveryCity(updateRentalRequest.getDeliveryCity());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setReturnStatus(updateRentalRequest.isReturnStatus());
		rental.setEndMileage(updateRentalRequest.getEndMileage());
		rental.setAdditionalServices(convertAdditionalServiceFromDto(updateRentalRequest.getAdditionalServiceDtos()));
		rental.setAmount(amount);
		rental.setCar(car);
		rental.setCustomer(individualCustomer);

		this.rentalDao.save(rental);
		car.setCurrentMileage(updateRentalRequest.getEndMileage());
		car.setCity(updateRentalRequest.getDeliveryCity());
		this.carDao.save(car);

		return new SuccessResult(Messages.RENTALUPDATE);
	}

	private Result checkIndiviualCustomerFindexPoint(IndividualCustomer individualCustomer, Car car) {

		if (this.allFindexPointCheckService.checkIndividualCustomerFindexPoint(individualCustomer) <= car
				.getCarFindeks()) {

			return new ErrorResult(Messages.CUSTOMERFINDEXPOINTERROR);
		}
		return new SuccessResult();
	}

	private Result checkCorporateCustomerFindexPoint(CorporateCustomer corporateCustomer, Car car) {

		if (this.allFindexPointCheckService.checkCorporateCustomerFindexPoint(corporateCustomer) <= car
				.getCarFindeks()) {

			return new ErrorResult(Messages.CUSTOMERFINDEXPOINTERROR);
		}
		return new SuccessResult();
	}

	private Result checkCarIsReturned(int carId) {

		RentalDetailsDto rentalDetailsDto = this.rentalDao.getByCarIdWhereReturnDateIsNull(carId);
		if (rentalDetailsDto != null) {
			return new ErrorResult(Messages.RENTALDATEERROR);
		}
		return new SuccessResult();

	}

	private Result checkIsCarCare(int carId) {

		if (this.carRepairDao.getByCar_CarId(carId).size() != 0) {
			CarRepair carRepair = this.carRepairDao.getByCar_CarId(carId)
					.get(this.carRepairDao.getByCar_CarId(carId).size() - 1);

			if (carRepair.getReturnDate() == null) {
				return new ErrorResult(Messages.RENTREPAIRERROR);
			}

		}
		return new SuccessResult();
	}

	private Result saveCardInformation(CreditCardDto creditCardDto, int customerId) {

		CreateCreditCardRequest createCreditCardRequest = new CreateCreditCardRequest();
		createCreditCardRequest.setCardName(creditCardDto.getCardName());
		createCreditCardRequest.setCardNumber(creditCardDto.getCardNumber());
		createCreditCardRequest.setPullDate(creditCardDto.getPullDate());
		createCreditCardRequest.setCvc(creditCardDto.getCvc());
		createCreditCardRequest.setCustomerId(customerId);

		return new SuccessResult(this.creditCardService.add(createCreditCardRequest).getMessage());
	}

	public Result checkPaymentControl(CreditCardDto creditCardDto, double price) {

		CreatePosRequest createPosRequest = new CreatePosRequest();

		createPosRequest.setCardName(creditCardDto.getCardName());
		createPosRequest.setCardNumber(creditCardDto.getCardNumber());
		createPosRequest.setCvc(creditCardDto.getCvc());
		createPosRequest.setPullDate(creditCardDto.getPullDate());
		createPosRequest.setPrice(price);

		if (!this.creditCardValidatorService.isValid(createPosRequest)) {
			return new ErrorResult(Messages.InsufficientBalance);
		}
		return new SuccessResult(Messages.SUCCESS);

	}

	private double calculateTotalAmountofRental(int carId, Date rentDate, Date returnDate, String deliveryCity,
			String pickUpCity, List<AdditionalServiceDto> additionalServiceDtos) {

		Car car = this.carDao.getById(carId);

		long rentDays = (ChronoUnit.DAYS.between((rentDate.toInstant()), (returnDate.toInstant())));

		double amount = rentDays * car.getDailyPrice();

		List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
		for (AdditionalServiceDto additionalServiceDtoItem : additionalServiceDtos) {
			additionalServices
					.add(this.additionalServiceDao.findById(additionalServiceDtoItem.getAdditionalId()).get());
		}

		if (!deliveryCity.equals(pickUpCity)) {
			amount += 500;
		}
		for (AdditionalService additionalServiceItem : additionalServices) {
			amount += additionalServiceItem.getAdditionalPrice() * rentDays;
		}

		return amount;
	}

	private List<AdditionalService> convertAdditionalServiceFromDto(List<AdditionalServiceDto> additionalServiceDtos) {

		List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();

		for (AdditionalServiceDto additionalServiceDtoItem : additionalServiceDtos) {
			additionalServices
					.add(this.additionalServiceDao.findById(additionalServiceDtoItem.getAdditionalId()).get());
		}

		return additionalServices;
	}

}
