package com.driver.delivery.managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driver.delivery.managment.entity.Data;

@Repository
public interface DataRepository<D extends Data> extends JpaRepository<D, Long> {
}