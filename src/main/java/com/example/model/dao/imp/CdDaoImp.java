package com.example.model.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Cd;
import com.example.model.dao.CdDao;

/**
 * 
 * @author yandypiedra
 *
 */
@Repository
//@Transactional
public class CdDaoImp implements CdDao {
    
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
