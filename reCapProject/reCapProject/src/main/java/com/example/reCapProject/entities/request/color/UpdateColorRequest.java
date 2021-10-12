package com.example.reCapProject.entities.request.color;



import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {

	private int colorId;

	private String colorName;
}
