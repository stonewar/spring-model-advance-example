package ch.example.testmodel;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.example.testmodel.model.Cd;
import ch.example.testmodel.repository.CdRespository;
/**
 * 
 * @author yandypiedra
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestModelConfig.class)
public class ConfigTes {

	@Autowired
	CdRespository repo;
	
	@Test
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