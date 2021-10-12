package com.example.reCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reCapProject.entities.concretes.AdditionalService;



public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

	boolean existsAdditionalServiceByAdditionalName(String additionalName);
	
	List<AdditionalService> getByRentals_RentalId(int rentalId);
	

	

}
