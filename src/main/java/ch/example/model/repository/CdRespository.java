package ch.example.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cd;

public interface CdRespository extends JpaRepository<Cd, Long>{
	//Cd may be omitted
	public Cd findCdByName(String name);
}
