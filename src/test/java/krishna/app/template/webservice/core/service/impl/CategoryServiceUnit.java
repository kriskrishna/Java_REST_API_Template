package boeing.app.template.webservice.core.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import boeing.app.template.webservice.TestUtil;
import boeing.app.template.webservice.core.model.entity.Category;
import boeing.app.template.webservice.core.repository.CategoryRepository;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;

@RunWith(SpringRunner.class)
public class CategoryServiceUnit {
	
	@Mock
	private CategoryRepository mockRepo;
	
	@InjectMocks
	private CategoryService testObj;

	@Test
	public void getAllTest() {
		
		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		List<Category> resourceList = new ArrayList<Category>();
		resourceList.add(resource);
		
		// Define mock object behavior:
		when(mockRepo.findAll()).thenReturn(resourceList);
		
		// Define expected results:
		List<Category> expectedResult = resourceList;
		
		// Call test method:
		List<Category> actualResult = testObj.getAll();
		
		// Validate dependencies were called
		verify(mockRepo, times(1)).findAll();
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	public void getOneTest() throws NotFoundException {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockRepo.findOne(anyInt())).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.getOne(resource.getId());
		
		// Validate dependencies were called
		verify(mockRepo, times(1)).findOne(anyInt());
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test(expected=NotFoundException.class)
	public void getOneNotFoundExceptionTest() throws NotFoundException {
		
		// Define mock object behavior:
		when(mockRepo.findOne(anyInt())).thenReturn(null);
		
		// Call test method:
		testObj.getOne(TestUtil.dummyInteger());
		
	}
	
	@Test
	public void createTest() throws ConflictException {

		// Define dummy data:
		Category resource = new Category();
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockRepo.saveAndFlush(any(Category.class))).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.create(resource);
		
		// Validate dependencies were called
		verify(mockRepo, times(1)).saveAndFlush(any(Category.class));
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test(expected=ConflictException.class)
	public void createConflictExceptionTest() throws ConflictException {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		
		// Call test method:
		testObj.create(resource);
		
	}
	
	@Test
	public void updateTest() throws NotFoundException, BadRequestException {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockRepo.exists(anyInt())).thenReturn(true);
		when(mockRepo.saveAndFlush(any(Category.class))).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.update(resource.getId(), resource);
		
		// Validate dependencies were called
		verify(mockRepo, times(1)).exists(anyInt());
		verify(mockRepo, times(1)).saveAndFlush(any(Category.class));
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test(expected=NotFoundException.class)
	public void updateNotFoundExceptionTest() throws NotFoundException, BadRequestException {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		
		// Define mock object behavior:
		when(mockRepo.exists(anyInt())).thenReturn(false);
		
		// Call test method:
		testObj.update(resource.getId(), resource);
		
	}

	@Test(expected=BadRequestException.class)
	public void updateBadRequestExceptionTest() throws NotFoundException, BadRequestException {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		
		// Define mock object behavior:
		when(mockRepo.exists(anyInt())).thenReturn(true);
		
		// Call test method:
		testObj.update(TestUtil.dummyInteger(), resource);
		
	}
	
	@Test
	public void deleteTest() throws NotFoundException {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockRepo.findOne(anyInt())).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.delete(resource.getId());
		
		// Validate dependencies were called
		verify(mockRepo, times(1)).findOne(anyInt());
		verify(mockRepo, times(1)).delete(anyInt());
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test(expected=NotFoundException.class)
	public void deleteNotFoundExceptionTest() throws NotFoundException {

		// Define mock object behavior:
		when(mockRepo.findOne(anyInt())).thenReturn(null);
		
		// Call test method:
		testObj.delete(TestUtil.dummyInteger());
		
	}
	
}
