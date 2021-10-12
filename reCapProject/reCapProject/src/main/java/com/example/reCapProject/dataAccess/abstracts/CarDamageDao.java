package com.example.reCapProject.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reCapProject.entities.concretes.CarDamage;

public interface CarDamageDao extends JpaRepository<CarDamage, Integer>{
	
	List<CarDamage> getByCar_CarId(int carId);

}
