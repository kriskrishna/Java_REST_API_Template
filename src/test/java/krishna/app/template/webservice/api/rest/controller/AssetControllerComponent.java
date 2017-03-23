package boeing.app.template.webservice.api.rest.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import boeing.app.template.webservice.ComponentTestBase;
import boeing.app.template.webservice.TestUtil;
import boeing.app.template.webservice.api.rest.exception.ApiError;
import boeing.app.template.webservice.core.model.entity.Asset;
import boeing.app.template.webservice.core.service.exception.BadRequestException;
import boeing.app.template.webservice.core.service.exception.ConflictException;
import boeing.app.template.webservice.core.service.exception.NotFoundException;
import boeing.app.template.webservice.core.service.impl.AssetService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssetControllerComponent extends ComponentTestBase {

	@MockBean
	private AssetService mockSvc;
	
	@InjectMocks
	private AssetController testObj;
	
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void before() {
		
		// Configure context to use mock dependencies:
		MockitoAnnotations.initMocks(this);
		
	};
	
	@Test
	public void getAllTest() throws Exception {
		
		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		List<Asset> resourceList = new ArrayList<Asset>();
		resourceList.add(resource);

		// Define mock object behavior:
		when(mockSvc.getAll()).thenReturn(resourceList);
		
		// Define expected results:
		String expectedResult = serializeJSON(resourceList);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(AssetController.PATH)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).getAll();
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}
	
	@Test
	public void getOneTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.getOne(anyInt())).thenReturn(resource);
		
		// Define expected results:
		String expectedResult = serializeJSON(resource);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(AssetController.PATH + "/" + resource.getId())
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).getOne(anyInt());
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}

	@Test
	public void getOneNotFoundExceptionTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new NotFoundException(AssetService.NotFoundMessage))
			.when(mockSvc).getOne(anyInt());

		// Define expected results:
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, AssetService.NotFoundMessage);
		String expectedResult = serializeJSON(apiError);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			get(AssetController.PATH + "/" + resource.getId())
				.accept(MediaType.APPLICATION_JSON)
		);

		// Validate dependencies were called:
		verify(mockSvc, times(1)).getOne(anyInt());
		
		// Validate results:
		actualResult
			.andExpect(status().isNotFound())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}

	@Test
	public void postTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.create(any(Asset.class))).thenReturn(resource);
		
		// Define expected results:
		String expectedResult = serializeJSON(resource);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			post(AssetController.PATH)
				.content(serializeJSON(resource))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).create(any(Asset.class));
		
		// Validate results:
		actualResult
			.andExpect(status().isCreated())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}

	@Test
	public void postConflictExceptionTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new ConflictException(AssetService.ConflictMessage)).when(mockSvc).create(any(Asset.class));

		// Define expected results:
		ApiError apiError = new ApiError(HttpStatus.CONFLICT, AssetService.ConflictMessage);
		String expectedResult = serializeJSON(apiError);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			post(AssetController.PATH)
				.content(serializeJSON(resource))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		);

		// Validate dependencies were called:
		verify(mockSvc, times(1)).create(any(Asset.class));
		
		// Validate results:
		actualResult
			.andExpect(status().isConflict())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}
	
	@Test
	public void putTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.update(anyInt(), any(Asset.class))).thenReturn(resource);
		
		// Define expected results:
		String expectedResult = serializeJSON(resource);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			put(AssetController.PATH + "/" + TestUtil.dummyInteger())
				.content(serializeJSON(resource))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).update(anyInt(), any(Asset.class));
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}

	@Test
	public void putBadRequestExceptionTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new BadRequestException(AssetService.BadRequestMessage))
			.when(mockSvc).update(anyInt(), any(Asset.class));

		// Define expected results:
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, AssetService.BadRequestMessage);
		String expectedResult = serializeJSON(apiError);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			put(AssetController.PATH + "/" + TestUtil.dummyInteger())
				.content(serializeJSON(resource))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		);

		// Validate dependencies were called:
		verify(mockSvc, times(1)).update(anyInt(), any(Asset.class));
		
		// Validate results:
		actualResult
			.andExpect(status().isBadRequest())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}
	
	@Test
	public void putNotFoundExceptionTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		doThrow(new NotFoundException(AssetService.NotFoundMessage))
			.when(mockSvc).update(anyInt(), any(Asset.class));

		// Define expected results:
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, AssetService.NotFoundMessage);
		String expectedResult = serializeJSON(apiError);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			put(AssetController.PATH + "/" + TestUtil.dummyInteger())
				.content(serializeJSON(resource))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
		);

		// Validate dependencies were called:
		verify(mockSvc, times(1)).update(anyInt(), any(Asset.class));
		
		// Validate results:
		actualResult
			.andExpect(status().isNotFound())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}
	
	@Test
	public void deleteTest() throws Exception {

		// Define dummy data:
		Asset resource = new Asset();
		resource.setId(TestUtil.dummyInteger());
		resource.setDescription(TestUtil.dummyString());
		resource.setName(TestUtil.dummyString());
		
		// Define mock object behavior:
		when(mockSvc.delete(anyInt())).thenReturn(resource);
		
		// Define expected results:
		String expectedResult = serializeJSON(resource);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			delete(AssetController.PATH + "/" + TestUtil.dummyInteger())
				.accept(MediaType.APPLICATION_JSON)
		);
		
		// Validate dependencies were called:
		verify(mockSvc, times(1)).delete(anyInt());
		
		// Validate results:
		actualResult
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}

	@Test
	public void deleteNotFoundExceptionTest() throws Exception {
		
		// Define mock object behavior:
		doThrow(new NotFoundException(AssetService.NotFoundMessage))
			.when(mockSvc).delete(anyInt());

		// Define expected results:
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, AssetService.NotFoundMessage);
		String expectedResult = serializeJSON(apiError);
		
		// Call test method:
		ResultActions actualResult = mockMvc.perform(
			delete(AssetController.PATH + "/" + TestUtil.dummyInteger())
				.accept(MediaType.APPLICATION_JSON)
		);

		// Validate dependencies were called:
		verify(mockSvc, times(1)).delete(anyInt());
		
		// Validate results:
		actualResult
			.andExpect(status().isNotFound())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(expectedResult));
		
	}
	
}
