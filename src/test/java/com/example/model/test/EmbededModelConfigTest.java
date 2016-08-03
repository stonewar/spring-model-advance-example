package com.example.model.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Cd;
import com.example.model.config.EmbededModelConfig;
import com.example.model.dao.CdDao;

/**
 * 
 * @author yandypiedra
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EmbededModelConfig.class)
public class EmbededModelConfigTest {

	@Autowired
	private CdDao rep;
	
	@Test
	public void isInitialized(){
		Assert.assertNotNull(rep);
	}
	
	@Test
	@Transactional
	public void testSaveCd(){
		Cd cd1 = new Cd();
		cd1.setName("Spring Spring");
		rep.save(cd1);
		
		Cd foundCd = rep.findCd(cd1.getId());
		Assert.assertEquals(cd1.getName(), foundCd.getName());
	}
}
