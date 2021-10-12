package com.example.reCapProject.entities.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceDetailDto {

	private int additionalId;

	@JsonIgnore
	private double additionalPrice;

	private String additionalName;

}
