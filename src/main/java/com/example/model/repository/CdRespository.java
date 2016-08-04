package com.example.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Cd;

public interface CdRespository extends CrudRepository<Cd, Long>{
	//Cd may be omitted
	public Cd findCdByName(String name);
}
