package com.example.reCapProject.core.services;

public class PosService {

	public boolean posServiceVerification(String cvc, String cardNumber, String pullDate, double price) {
		
		
		double limit = 3500;

		if (price <= limit) {

			return true;
		}

		return false;
	}
}
