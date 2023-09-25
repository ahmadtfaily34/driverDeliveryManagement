package com.driver.delivery.managment.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.driver.delivery.managment.entity.Order;
import com.driver.delivery.managment.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService extends DataService<Order> {

	public OrderService(OrderRepository orderRepository) {
		super(orderRepository);
	}

	@Transactional
	public List<Order> findByVehicle(String platNumber) {
		return ((OrderRepository) dataRepository).findByVehicle_PlatNumber(platNumber);
	}
}
