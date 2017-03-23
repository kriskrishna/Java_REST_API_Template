package boeing.app.template.webservice.core.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import boeing.app.template.webservice.TestUtil;
import boeing.app.template.webservice.core.model.entity.Asset;

// TODO : run integration tests with different db connection (test profile)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AssetRepositoryIntegration {
	
	@Autowired
	private AssetRepository testObj;
	
	@Test
	public void crudTest() {
		
		// Create entity:
		Asset toBeInserted = new Asset();
		toBeInserted.setDescription(TestUtil.dummyString());
		toBeInserted.setName(TestUtil.dummyString());
		
		// Check ID is assigned by data source:
		Asset inserted = testObj.save(toBeInserted);
		assertTrue(inserted.getId() > 0);
		
		// Check existence:
		boolean exists = testObj.exists(inserted.getId());
		assertTrue(exists);
		
		// Get one:
		Asset gotten = testObj.getOne(inserted.getId());
		assertEquals(inserted, gotten);
		
		// Create another entity:
		Asset toBeUpdated = new Asset();
		toBeUpdated.setId(inserted.getId());
		toBeUpdated.setDescription(TestUtil.dummyString());
		toBeUpdated.setName(TestUtil.dummyString());
		
		// Check equality on updated and toBeUpdated:
		Asset updated = testObj.save(toBeUpdated);
		assertEquals(updated, toBeUpdated);
		
		// Check count:
		long initCount = testObj.count();
		assertTrue(initCount > 0);
		
		// Delete:
		testObj.delete(inserted);
		boolean isDeleted = testObj.exists(inserted.getId());
		assertFalse(isDeleted);
		
	}
	
}
