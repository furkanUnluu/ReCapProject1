package com.example.reCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reCapProject.business.abstracts.CreditCardService;
import com.example.reCapProject.core.utilities.result.DataResult;
import com.example.reCapProject.core.utilities.result.Result;
import com.example.reCapProject.entities.dtos.CreditCardDto;
import com.example.reCapProject.entities.request.creditCard.CreateCreditCardRequest;
import com.example.reCapProject.entities.request.creditCard.DeleteCreditCardRequest;
import com.example.reCapProject.entities.request.creditCard.UpdateCreditCardRequest;

@RestController
@RequestMapping("api/credit_cards")
public class CreditCardsController {

	CreditCardService creditCardService;

	public CreditCardsController(CreditCardService creditCardService) {
		super();
		this.creditCardService = creditCardService;
	}

	@GetMapping("/getall")
	public DataResult<List<CreditCardDto>> getAll() {
		return this.creditCardService.getAll();
	}

	@GetMapping("/getcreditcardbycustomerid")
	public DataResult<List<CreditCardDto>> getCreditCardByCustomerId (int userId) {
		return this.creditCardService.getCreditCardByCustomerId(userId);
	}
	
	@PostMapping("/add")
	public Result add(@Valid @RequestBody CreateCreditCardRequest createCreditCardRequest) {
		return this.creditCardService.add(createCreditCardRequest);
	}

	@PostMapping("update")
	public Result update(@Valid @RequestBody UpdateCreditCardRequest updateCreditCardRequest) {
		return this.creditCardService.update(updateCreditCardRequest);
	}

	@PutMapping("delete")
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
		return this.creditCardService.delete(deleteCreditCardRequest);
	}

}
