package com.driver.delivery.managment.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class VehicleMaintenance extends Data {
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "vehicleId", nullable = false)
	private Vehicle vehicle;

	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MaintenanceType type;

	@NotNull
	@Column(nullable = false)
	private LocalDate date;

	@NotNull
	@Column(nullable = false)
	private BigDecimal cost;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public MaintenanceType getType() {
		return type;
	}

	public void setType(MaintenanceType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public void copy(Object data) {
		if (data instanceof VehicleMaintenance) {
			VehicleMaintenance vehicleMaintenance = (VehicleMaintenance) data;
			this.setVehicle(vehicleMaintenance.getVehicle());
			this.setDate(vehicleMaintenance.getDate());
			this.setCost(vehicleMaintenance.getCost());
			this.setType(vehicleMaintenance.getType());
		}
	}
}
