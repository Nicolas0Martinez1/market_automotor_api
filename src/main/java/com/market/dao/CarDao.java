package com.market.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.Entitys.Cars;

public interface CarDao extends JpaRepository<Cars, Long> {

	List<Cars> findAll();

		
}
