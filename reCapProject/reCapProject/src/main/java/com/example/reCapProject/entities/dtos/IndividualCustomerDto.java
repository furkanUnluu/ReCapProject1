package com.example.reCapProject.entities.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {
	
	private int userId;
	private String firstName;

	private String lastName;

	private String identityNumber;

	private String email;

	private String password;

}
