package com.driver.delivery.managment.entity;

import static com.driver.delivery.managment.entity.TestHelper.randomString;
import static com.driver.delivery.managment.entity.TestHelper.strictlyPositiveLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DriverTest {
	public static Driver createDriver() {
		Driver driver = new Driver();
		driver.setId(strictlyPositiveLong());
		driver.setFirstName(randomString());
		driver.setLastName(randomString());
		driver.setDob(LocalDate.of(2024, 2, 2));
		driver.setLicense(randomString());
		driver.setPhoneNumber(randomString());
		return driver;
	}

	@Test
	void testCopy() {
		// Given
		Driver copy = createDriver();
		Driver driver = new Driver();

		// When
		driver.copy(copy);

		// Then
		assertNull(driver.getId());
		assertEquals(driver.getFirstName(), copy.getFirstName());
		assertEquals(driver.getLastName(), copy.getLastName());
		assertEquals(driver.getDob(), copy.getDob());
		assertEquals(driver.getLicense(), copy.getLicense());
		assertEquals(driver.getPhoneNumber(), copy.getPhoneNumber());
	}

}
