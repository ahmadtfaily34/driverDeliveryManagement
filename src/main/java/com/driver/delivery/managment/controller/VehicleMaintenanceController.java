package com.driver.delivery.managment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.driver.delivery.managment.entity.VehicleMaintenance;
import com.driver.delivery.managment.services.VehicleMaintenanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehicleMaintenance")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
public class VehicleMaintenanceController {

	@Autowired
	private VehicleMaintenanceService vehicleMaintenanceService;

	@GetMapping
	public ResponseEntity<List<VehicleMaintenance>> findAllEntities() throws IllegalAccessException {
		List<VehicleMaintenance> data = vehicleMaintenanceService.findAllEntities();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping({ "/{id}" })
	public ResponseEntity<VehicleMaintenance> readEntity(@PathVariable Long id) throws IllegalAccessException {
		return new ResponseEntity<>(vehicleMaintenanceService.readEntity(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<VehicleMaintenance> saveEntity(@RequestBody @Valid VehicleMaintenance data)
			throws IllegalAccessException {
		VehicleMaintenance inserted = vehicleMaintenanceService.insertEntity(data);
		return new ResponseEntity<>(inserted, HttpStatus.CREATED);
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<VehicleMaintenance> updateEntity(@PathVariable("id") Long id,
			@RequestBody VehicleMaintenance data) throws IllegalAccessException {
		VehicleMaintenance updated = vehicleMaintenanceService.updateEntity(id, data);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping({ "/{id}" })
	public ResponseEntity<Void> deleteEntity(@PathVariable("id") Long id) throws IllegalAccessException {
		vehicleMaintenanceService.deleteEntity(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
