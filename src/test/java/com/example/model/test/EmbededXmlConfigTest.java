package com.example.model.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Cd;
import com.example.model.dao.CdDao;
/**
 * 
 * @author yandypiedra
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:embeded-config.xml")
public class EmbededXmlConfigTest {


	@Autowired
	private CdDao rep;
	
	@Before
	@Test
	public void isInitialized(){
		assertNotNull(rep);
	}
	
	@Test
	@Transactional
	public void testSaveCd(){
		Cd cd1 = new Cd();
		cd1.setName("Spring Spring");
		rep.save(cd1);
		Cd foundCd = rep.findCd(cd1.getId());
		assertEquals(cd1.getName(), foundCd.getName());
	}

}
