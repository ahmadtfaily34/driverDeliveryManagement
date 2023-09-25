package com.driver.delivery.managment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.driver.delivery.managment.entity.Vehicle;

@Repository
public interface VehicleRepository extends DataRepository<Vehicle> {

	List<Vehicle> findByDriver_FirstNameAndDriver_LastName(String firstName, String lastName);
}