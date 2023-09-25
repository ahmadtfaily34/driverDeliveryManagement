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

import com.driver.delivery.managment.entity.Order;
import com.driver.delivery.managment.entity.Vehicle;
import com.driver.delivery.managment.services.OrderService;
import com.driver.delivery.managment.services.VehicleService;

@RestController
@RequestMapping("/api/order")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MANAGER')")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private VehicleService vehicleService;

	@GetMapping
	public ResponseEntity<List<Order>> findAllEntities() throws IllegalAccessException {
		List<Order> data = orderService.findAllEntities();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping("/vehicle")
	public ResponseEntity<List<Order>> findByVehicle(@RequestParam String platNumber) throws IllegalAccessException {
		List<Order> data = orderService.findByVehicle(platNumber);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping({ "/{id}" })
	public ResponseEntity<Order> readEntity(@PathVariable Long id) throws IllegalAccessException {
		return new ResponseEntity<>(orderService.readEntity(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Order> saveEntity(@RequestBody Order data) throws IllegalAccessException {
		Order inserted = orderService.insertEntity(data);
		return new ResponseEntity<>(inserted, HttpStatus.CREATED);
	}

	@PutMapping({ "/{id}" })
	public ResponseEntity<Order> updateEntity(@PathVariable("id") Long id, @RequestBody Order data)
			throws IllegalAccessException {
		Order updated = orderService.updateEntity(id, data);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@DeleteMapping({ "/{id}" })
	public ResponseEntity<Void> deleteEntity(@PathVariable("id") Long id) throws IllegalAccessException {
		orderService.deleteEntity(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/{id}/vehicle/{vehicleId}")
	public ResponseEntity<Void> linkVehicle(@PathVariable(value = "id") Long orderId,
			@PathVariable(value = "vehicleId") Long vehicleId) throws IllegalAccessException {
		Vehicle vehicle = vehicleService.readEntity(vehicleId);
		Order order = orderService.readEntity(orderId);
		order.setVehicle(vehicle);
		orderService.updateEntity(orderId, order);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
