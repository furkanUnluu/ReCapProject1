package com.example.reCapProject.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
	private int invoiceId;
	private String invoiceNumber;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date rentDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date returnDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date invoiceDate;

	private double invoiceAmount;
	
	private List<AdditionalServiceDetailDto> additionalServiceDetailDtos;
	
	
	

	
	

	


}
