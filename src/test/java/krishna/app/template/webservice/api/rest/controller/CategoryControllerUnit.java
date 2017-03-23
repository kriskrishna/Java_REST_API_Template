package boeing.app.template.webservice.api.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
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
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;
import boeing.app.template.webservice.core.service.impl.CategoryService;

@RunWith(SpringRunner.class)
public class CategoryControllerUnit {

	@Mock
	private CategoryService mockSvc;
	
	@InjectMocks
	private CategoryController testObj;
	
	@Test
	public void getAllTest() throws Exception {
		
		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		List<Category> resourceList = new ArrayList<Category>();
		resourceList.add(resource);

		// Define mock object behavior:
		when(mockSvc.getAll()).thenReturn(resourceList);
		
		// Define expected results:
		List<Category> expectedResult = resourceList;
		
		// Call test method:
		List<Category> actualResult = testObj.getAll().getBody();
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).getAll();
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}
	
	@Test
	public void getOneTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.getOne(anyInt())).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.getOne(TestUtil.dummyInteger()).getBody();
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).getOne(anyInt());
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}

	@Test(expected=NotFoundException.class)
	public void getOneNotFoundExceptionTest() throws Exception {

		// Define mock object behavior:
		doThrow(new NotFoundException(CategoryService.NotFoundMessage))
			.when(mockSvc).getOne(anyInt());

		// Call test method:
		testObj.getOne(TestUtil.dummyInteger());
	}

	@Test
	public void postTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.create(any(Category.class))).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.post(resource).getBody();
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).create(any(Category.class));
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}

	@Test(expected=ConflictException.class)
	public void postConflictExceptionTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new ConflictException(CategoryService.ConflictMessage)).when(mockSvc).create(any(Category.class));

		// Call test method:
		testObj.post(resource);
		
	}
	
	@Test
	public void putTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.update(anyInt(), any(Category.class))).thenReturn(resource);
		
		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.put(resource.getId(), resource).getBody();
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).update(anyInt(), any(Category.class));
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
	}

	@Test(expected=BadRequestException.class)
	public void putBadRequestExceptionTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new BadRequestException(CategoryService.BadRequestMessage))
			.when(mockSvc).update(anyInt(), any(Category.class));

		// Call test method:
		testObj.put(TestUtil.dummyInteger(), resource);
		
	}
	
	@Test(expected=NotFoundException.class)
	public void putNotFoundExceptionTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new NotFoundException(CategoryService.NotFoundMessage))
			.when(mockSvc).update(anyInt(), any(Category.class));

		// Call test method:
		testObj.put(TestUtil.dummyInteger(), resource);
	}
	
	@Test
	public void deleteTest() throws Exception {

		// Define dummy data:
		Category resource = new Category();
		resource.setId(TestUtil.dummyInteger());
		resource.setLabel(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.delete(anyInt())).thenReturn(resource);

		// Define expected results:
		Category expectedResult = resource;
		
		// Call test method:
		Category actualResult = testObj.delete(resource.getId()).getBody();
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).delete(anyInt());
		
		// Validate results:
		assertEquals(expectedResult, actualResult);
		
		
	}

	@Test(expected=NotFoundException.class)
	public void deleteNotFoundExceptionTest() throws Exception {
		
		// Define mock object behavior:
		doThrow(new NotFoundException(CategoryService.NotFoundMessage))
			.when(mockSvc).delete(anyInt());

		// Call test method:
		testObj.delete(TestUtil.dummyInteger());
		
	}
	
}
