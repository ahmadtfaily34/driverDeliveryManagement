package com.driver.delivery.managment.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "orders")
public class Order extends Data {
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "vehicleId", nullable = true)
	private Vehicle vehicle;

	@NotBlank
	@Column(nullable = false)
	private String customerName;

	@NotBlank
	@Column(nullable = false)
	private String customerLocation;

	@NotNull
	@Column(nullable = false)
	private LocalDate deliveryDate;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@Override
	public void copy(Object data) {
		if (data instanceof Order) {
			Order order = (Order) data;
			this.setVehicle(order.getVehicle());
			this.setCustomerLocation(order.getCustomerLocation());
			this.setCustomerName(order.getCustomerName());
			this.setDeliveryDate(order.getDeliveryDate());
		}
	}
}
