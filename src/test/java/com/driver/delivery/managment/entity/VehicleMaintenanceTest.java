package com.driver.delivery.managment.entity;

import static com.driver.delivery.managment.entity.TestHelper.strictlyPositiveLong;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class VehicleMaintenanceTest {

	public static VehicleMaintenance createVehicleMaintenance() {
		VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();
		vehicleMaintenance.setId(strictlyPositiveLong());
		vehicleMaintenance.setCost(BigDecimal.valueOf(strictlyPositiveLong()));
		vehicleMaintenance.setDate(LocalDate.of(2023, 9, 25));
		vehicleMaintenance.setType(MaintenanceType.OIL_CHANGE);
		vehicleMaintenance.setVehicle(VehicleTest.createVehicle());
		return vehicleMaintenance;
	}

	@Test
	void testCopy() {
		// Given
		VehicleMaintenance copy = createVehicleMaintenance();
		VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();

		// When
		vehicleMaintenance.copy(copy);

		// Then
		assertNull(vehicleMaintenance.getId());
		assertEquals(vehicleMaintenance.getCost(), copy.getCost());
		assertEquals(vehicleMaintenance.getDate(), copy.getDate());
		assertEquals(vehicleMaintenance.getType(), copy.getType());
		assertEquals(vehicleMaintenance.getVehicle().getId(), copy.getVehicle().getId());
	}

}
