package com.driver.delivery.managment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.driver.delivery.managment.entity.Vehicle;
import com.driver.delivery.managment.repository.VehicleRepository;

import jakarta.transaction.Transactional;

@Service
public class VehicleService extends DataService<Vehicle> {

	public VehicleService(VehicleRepository vehicleRepository) {
		super(vehicleRepository);
	}

	@Transactional
	public List<Vehicle> findByDriver(String firstName, String lastName) {
		return ((VehicleRepository) dataRepository).findByDriver_FirstNameAndDriver_LastName(firstName, lastName);
	}
}
