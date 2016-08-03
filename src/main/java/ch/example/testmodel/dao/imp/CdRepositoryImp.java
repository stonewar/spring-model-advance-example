package ch.example.testmodel.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ch.example.testmodel.dao.CdRepository;
import ch.example.testmodel.model.Cd;

/**
 * 
 * @author yandypiedra
 *
 */
@Repository
//@Transactional
public class CdRepositoryImp implements CdRepository {
    
	@PersistenceContext
	private EntityManager em;
	
	public Long save(Cd cd) {
		em.persist(cd);
		return cd.getId();
	}

	public Cd findCd(Long id) {
		return em.find(Cd.class, id);
	}

}
