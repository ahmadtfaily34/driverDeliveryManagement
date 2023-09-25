package com.driver.delivery.managment.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "firstName", "lastName" }) })
public class Driver extends Data {

	@NotBlank
	@Column(nullable = false)
	private String firstName;

	@NotBlank
	@Column(nullable = false)
	private String lastName;

	@NotNull
	@Column(nullable = false)
	private LocalDate dob;

	@NotBlank
	@Column(nullable = false)
	private String license;

	@NotBlank
	@Column(nullable = false)
	private String phoneNumber;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void copy(Object data) {
		if (data instanceof Driver) {
			Driver driver = (Driver) data;
			this.setFirstName(driver.getFirstName());
			this.setLastName(driver.getLastName());
			this.setDob(driver.getDob());
			this.setLicense(driver.getLicense());
			this.setPhoneNumber(driver.getPhoneNumber());
		}
	}
}
