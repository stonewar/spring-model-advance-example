package ch.example.testmodel;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.example.testmodel.dao.CdRepository;
import ch.example.testmodel.model.Cd;

/**
 * 
 * @author yandypiedra
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EmbededJpaConfig.class)
public class EmbededJpaConfigTest {

	@Autowired
	private CdRepository rep;
	
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
