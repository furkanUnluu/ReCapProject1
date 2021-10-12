package com.example.reCapProject.entities.request.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {

	private int id;

	private String firstName;

	private String lastName;

	private String identityNumber;

	private String email;

	private String password;

}
