package com.driver.delivery.managment.entity;

import static com.driver.delivery.managment.entity.TestHelper.randomString;
import static com.driver.delivery.managment.entity.TestHelper.strictlyPositiveLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class OrderTest {

	private Order createOrder() {
		Order order = new Order();
		order.setId(strictlyPositiveLong());
		order.setCustomerLocation(randomString());
		order.setCustomerName(randomString());
		order.setDeliveryDate(LocalDate.of(2024, 2, 2));
		order.setVehicle(VehicleTest.createVehicle());
		return order;
	}

	@Test
	void testCopy() {
		// Given
		Order copy = createOrder();
		Order order = new Order();

		// When
		order.copy(copy);

		// Then
		assertNull(order.getId());
		assertEquals(order.getCustomerLocation(), copy.getCustomerLocation());
		assertEquals(order.getCustomerName(), copy.getCustomerName());
		assertEquals(order.getDeliveryDate(), copy.getDeliveryDate());
		assertEquals(order.getVehicle().getId(), copy.getVehicle().getId());
	}
}
