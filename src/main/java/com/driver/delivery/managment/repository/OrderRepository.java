package com.driver.delivery.managment.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.driver.delivery.managment.entity.Order;

@Repository
public interface OrderRepository extends DataRepository<Order> {

	List<Order> findByVehicle_PlatNumber(String platNumber);
}