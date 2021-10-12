package com.example.reCapProject.entities.dtos;

import java.util.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarRepairsDto {
	


	private int carRepairId;


	private Date returnDate;

	private String repairDescription;

	private String repairPrice;

	
	private Date repairDate;

	private boolean returnStatus;
	
	

}
