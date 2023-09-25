package com.driver.delivery.managment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vehicle extends Data {
	@NotBlank
	@Column(nullable = false)
	private String brand;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String platNumber;

	@JsonIgnore
	@Lob
	private String image;

	@NotBlank
	@Column(nullable = false)
	private String color;

	@NotNull
	@Column(nullable = false)
	private Long modelYear;

	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "driverId", nullable = true)
	private Driver driver;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPlatNumber() {
		return platNumber;
	}

	public void setPlatNumber(String platNumber) {
		this.platNumber = platNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getModelYear() {
		return modelYear;
	}

	public void setModelYear(Long modelYear) {
		this.modelYear = modelYear;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public void copy(Object data) {
		if (data instanceof Vehicle) {
			Vehicle vehicle = (Vehicle) data;
			this.setBrand(vehicle.getBrand());
			this.setColor(vehicle.getColor());
			this.setPlatNumber(vehicle.getPlatNumber());
			this.setModelYear(vehicle.getModelYear());
			this.setDriver(vehicle.getDriver());
		}
	}
}
