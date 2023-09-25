package com.driver.delivery.managment.services;

import java.util.ArrayList;
import java.util.List;

import com.driver.delivery.managment.entity.Data;
import com.driver.delivery.managment.repository.DataRepository;

public abstract class DataService<D extends Data> {
	protected final DataRepository<D> dataRepository;

	public DataService(DataRepository<D> dataRepository) {
		this.dataRepository = dataRepository;
	}

	public List<D> findAllEntities() {
		List<D> data = new ArrayList<>();
		dataRepository.findAll().forEach(data::add);
		return data;
	}

	public D readEntity(Long id) throws IllegalAccessException {
		return dataRepository.findById(id)
				.orElseThrow(() -> new IllegalAccessException("Not found entity with id = " + id));
	}

	public D insertEntity(D data) {
		return dataRepository.save(data);
	}

	public void deleteEntity(Long id) {
		dataRepository.deleteById(id);
	}

	public D updateEntity(Long id, D driver) throws IllegalAccessException {
		D driverFromDb = readEntity(id);
		driverFromDb.copy(driver);
		return dataRepository.save(driverFromDb);
	}
}
