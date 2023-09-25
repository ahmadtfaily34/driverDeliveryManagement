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

import com.driver.delivery.managment.entity.Driver;
import com.driver.delivery.managment.services.DriverService;

@RestController
@RequestMapping("/api/driver")
@PreAuthorize("hasAuthority('ADMIN')")
public class DriverController {
	@Autowired
	private DriverService driverService;

	@GetMapping
	public ResponseEntity<List<Driver>> findAllEntities() throws IllegalAccessException {
		List<Driver> data = driverService.findAllEntities();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping({ "/{id}" })
	public ResponseEntity<Driver> readEntity(@PathVariable Long id) throws IllegalAccessException {
		return new ResponseEntity<>(driverService.readEntity(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Driver> saveEntity(@RequestBody Driver data) throws IllegalAccessException {
		Driver inserted = driverService.insertEntity(data);
		return new ResponseEntity<>(inserted, HttpStatus.CREATED);
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<Driver> updateEntity(@PathVariable("id") Long id, @RequestBody Driver data)
			throws IllegalAccessException {
		Driver updated = driverService.updateEntity(id, data);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping({ "/{id}" })
	public ResponseEntity<Void> deleteEntity(@PathVariable("id") Long id) throws IllegalAccessException {
		driverService.deleteEntity(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
