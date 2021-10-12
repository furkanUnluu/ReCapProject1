package com.example.reCapProject.entities.request.additionalService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	
private int additionalId;
	
	
	private String additionalDescription;
	
	
	private double additionalPrice;
	
	
	private String additionalName;

}
