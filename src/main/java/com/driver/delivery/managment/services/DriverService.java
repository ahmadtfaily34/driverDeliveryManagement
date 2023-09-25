package com.driver.delivery.managment.services;

import org.springframework.stereotype.Service;

import com.driver.delivery.managment.entity.Driver;
import com.driver.delivery.managment.repository.DriverRepository;

@Service
public class DriverService extends DataService<Driver> {

	public DriverService(DriverRepository driverRepository) {
		super(driverRepository);
	}
}
