package com.example.model.dao;

import com.example.model.Cd;

/**
 * 
 * @author yandypiedra
 *
 */
public interface CdDao {

	public Long save(Cd cd);
	public Cd findCd(Long id);
}
