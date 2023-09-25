package com.driver.delivery.managment.services;

import org.springframework.stereotype.Service;

import com.driver.delivery.managment.entity.VehicleMaintenance;
import com.driver.delivery.managment.repository.VehicleMaintenanceRepository;

@Service
public class VehicleMaintenanceService extends DataService<VehicleMaintenance> {

	public VehicleMaintenanceService(VehicleMaintenanceRepository vehicleRepository) {
		super(vehicleRepository);
	}
}
