package ch.example.testmodel.dao;

import ch.example.testmodel.model.Cd;

/**
 * 
 * @author yandypiedra
 *
 */
public interface CdRepository {

	public Long save(Cd cd);
	public Cd findCd(Long id);
}
