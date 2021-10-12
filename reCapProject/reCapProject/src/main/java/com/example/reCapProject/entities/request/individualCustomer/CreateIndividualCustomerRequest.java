package com.example.reCapProject.entities.request.individualCustomer;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	@NotBlank
	@Size(max=11)
	private String identityNumber;
	
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
	
	
	
	
	

}
