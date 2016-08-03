package ch.example.testmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ch.example.testmodel.model.Cd;

public interface CdRespository extends JpaRepository<Cd, Long>{
	//Cd may be omitted
	public Cd findCdByName(String name);
}
