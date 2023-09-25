package com.driver.delivery.managment.entity;

import static com.driver.delivery.managment.entity.TestHelper.randomString;
import static com.driver.delivery.managment.entity.TestHelper.strictlyPositiveLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class VehicleTest {

	public static Vehicle createVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setId(strictlyPositiveLong());
		vehicle.setColor(randomString());
		vehicle.setBrand(randomString());
		vehicle.setPlatNumber(randomString());
		vehicle.setModelYear(100L);
		vehicle.setDriver(DriverTest.createDriver());
		return vehicle;
	}

	@Test
	void testCopy() {
		// Given
		Vehicle copy = createVehicle();
		Vehicle vehicle = new Vehicle();

		// When
		vehicle.copy(copy);

		// Then
		assertNull(vehicle.getId());
		assertNull(vehicle.getImage());
		assertEquals(vehicle.getColor(), copy.getColor());
		assertEquals(vehicle.getBrand(), copy.getBrand());
		assertEquals(vehicle.getPlatNumber(), copy.getPlatNumber());
		assertEquals(vehicle.getModelYear(), copy.getModelYear());
		assertEquals(vehicle.getDriver().getId(), copy.getDriver().getId());
	}
}
