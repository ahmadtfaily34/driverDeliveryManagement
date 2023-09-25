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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.driver.delivery.managment.entity.Driver;
import com.driver.delivery.managment.entity.Vehicle;
import com.driver.delivery.managment.services.DriverService;
import com.driver.delivery.managment.services.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
@PreAuthorize("hasAuthority('ADMIN')")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private DriverService driverService;

	@GetMapping
	public ResponseEntity<List<Vehicle>> findAllEntities() throws IllegalAccessException {
		List<Vehicle> data = vehicleService.findAllEntities();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/driver")
	public ResponseEntity<List<Vehicle>> findByDriver(@RequestParam String firstName, @RequestParam String lastName)
			throws IllegalAccessException {
		List<Vehicle> data = vehicleService.findByDriver(firstName, lastName);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping({ "/{id}" })
	public ResponseEntity<Vehicle> readEntity(@PathVariable Long id) throws IllegalAccessException {
		return new ResponseEntity<>(vehicleService.readEntity(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Vehicle> saveEntity(@RequestBody Vehicle data) throws IllegalAccessException {
		Vehicle inserted = vehicleService.insertEntity(data);
		return new ResponseEntity<>(inserted, HttpStatus.CREATED);
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<Vehicle> updateEntity(@PathVariable("id") Long id, @RequestBody Vehicle data)
			throws IllegalAccessException {
		Vehicle updated = vehicleService.updateEntity(id, data);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping({ "/{id}" })
	public ResponseEntity<Void> deleteEntity(@PathVariable("id") Long id) throws IllegalAccessException {
		vehicleService.deleteEntity(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/{id}/driver/{driverId}")
	public ResponseEntity<Void> allocateDriver(@PathVariable(value = "id") Long vehicleId, @PathVariable Long driverId)
			throws IllegalAccessException {
		Driver driver = driverService.readEntity(driverId);
		Vehicle vehicle = vehicleService.readEntity(vehicleId);
		vehicle.setDriver(driver);
		vehicleService.updateEntity(vehicleId, vehicle);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/{id}/image")
	public ResponseEntity<Void> addImage(@PathVariable(value = "id") Long vehicleId, @RequestBody String base64Image)
			throws IllegalAccessException {
		Vehicle vehicle = vehicleService.readEntity(vehicleId);
		vehicle.setImage(base64Image);
		vehicleService.updateEntity(vehicleId, vehicle);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}/image")
	public ResponseEntity<String> getImage(@PathVariable(value = "id") Long vehicleId) throws IllegalAccessException {
		Vehicle vehicle = vehicleService.readEntity(vehicleId);
		return new ResponseEntity<>(vehicle.getImage(), HttpStatus.OK);
	}
}
