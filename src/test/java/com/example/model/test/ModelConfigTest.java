package com.example.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.model.Cd;
import com.example.model.config.ModelConfig;
import com.example.model.repository.CdRespository;
/**
 * 
 * @author yandypiedra
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ModelConfig.class)
public class ModelConfigTest {

	@Autowired
	CdRespository repo;
	
	@Test
	@Before
	public void isCdRespositoryNotNull() {
		assertNotNull(repo);
	}
	
	@Test
	public void testCreateCd(){
		Cd cd = new Cd();
		cd.setName("The power of Love");
		repo.save(cd);	
		Cd foundCd = repo.findOne(cd.getId());
		assertEquals(cd.getName(), foundCd.getName());
	}

}
