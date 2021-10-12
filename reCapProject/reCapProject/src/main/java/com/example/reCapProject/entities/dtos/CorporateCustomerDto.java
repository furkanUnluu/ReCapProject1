package com.example.reCapProject.entities.dtos;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerDto {
	
	private int userId;
	private String companyName;

	private String taxNumber;
	
	private String password;

	private String email;

}
