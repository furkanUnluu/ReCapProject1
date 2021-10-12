package com.example.reCapProject.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

	private String cardName;

	private String cardNumber;

	private String pullDate;

	private String cvc;

}
