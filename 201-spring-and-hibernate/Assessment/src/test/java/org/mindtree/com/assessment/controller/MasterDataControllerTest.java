package org.mindtree.com.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mindtree.com.assessment.constants.ApplicationConstants;
import org.mindtree.com.assessment.constants.ExceptionCodes;
import org.mindtree.com.assessment.dto.MasterDataDTO;
import org.mindtree.com.assessment.dto.ResponseDTO;
import org.mindtree.com.assessment.exception.ApplicationException;
import org.mindtree.com.assessment.service.MasterDataService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MasterDataControllerTest {

	private MockMvc mockMvc;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@InjectMocks
	private MasterDataController masterDataController;
	@Mock
	private MasterDataService masterDataService;

	@BeforeEach
	public void initialize() {
		mockMvc = MockMvcBuilders.standaloneSetup(masterDataController).build();
	}

	@Test
	public void testGetZonesSuccess() throws Exception {
		List<MasterDataDTO> expectedZones = new ArrayList<>();
		MasterDataDTO zone = new MasterDataDTO();
		zone.setCode("Zone A");
		zone.setId(1);
		expectedZones.add(zone);
		zone = new MasterDataDTO();
		zone.setCode("Zone 2");
		zone.setId(2);
		expectedZones.add(zone);

		try {
			doReturn(expectedZones).when(masterDataService).getZones();

			ResponseDTO response = MAPPER.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/master-data/zone"))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertNotNull(response.getResponse());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetZonesException() throws Exception {
		try {
			doThrow(new ApplicationException(ExceptionCodes.MDS_ZONE_NOT_FOUND)).when(masterDataService).getZones();

			ResponseDTO response = MAPPER.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/master-data/zone"))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertTrue(errors.containsKey(ApplicationConstants.ZONE));
			assertEquals(ExceptionCodes.MDS_ZONE_NOT_FOUND, errors.get(ApplicationConstants.ZONE));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@Test
	public void testGetStatusesSucess() throws Exception {
		List<MasterDataDTO> expectedStatuses = new ArrayList<>();
		MasterDataDTO status = new MasterDataDTO();
		status.setCode("Owned");
		status.setId(1);
		expectedStatuses.add(status);
		status = new MasterDataDTO();
		status.setCode("Tentanted");
		status.setId(2);
		expectedStatuses.add(status);

		try {
			doReturn(expectedStatuses).when(masterDataService).getStatuses();

			ResponseDTO response = MAPPER.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/master-data/status"))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertNotNull(response.getResponse());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetStatusesNegative() throws Exception {
		try {
			doThrow(new ApplicationException(ExceptionCodes.MDS_STATUS_NOT_FOUND)).when(masterDataService)
					.getStatuses();

			ResponseDTO response = MAPPER.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/master-data/status"))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertTrue(errors.containsKey(ApplicationConstants.STATUS));
			assertEquals(ExceptionCodes.MDS_STATUS_NOT_FOUND, errors.get(ApplicationConstants.STATUS));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@Test
	public void testGetCategoriesWithoutZoneSuccess() throws Exception {
		List<MasterDataDTO> expectedCategories = new ArrayList<>();
		MasterDataDTO status = new MasterDataDTO();
		status.setCode("RCC");
		status.setId(1);
		expectedCategories.add(status);
		status = new MasterDataDTO();
		status.setCode("Roofed");
		status.setId(2);
		expectedCategories.add(status);

		try {
			doReturn(expectedCategories).when(masterDataService).getResidentialPropertyCategories();

			ResponseDTO response = MAPPER.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/master-data/category"))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertNotNull(response.getResponse());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}
	}

	@Test
	public void testGetCategoriesWithZoneSucces() throws Exception {
		List<MasterDataDTO> expectedCategories = new ArrayList<>();
		MasterDataDTO status = new MasterDataDTO();
		status.setCode("RCC");
		status.setId(1);
		expectedCategories.add(status);
		status = new MasterDataDTO();
		status.setCode("Roofed");
		status.setId(2);
		expectedCategories.add(status);

		try {
			doReturn(expectedCategories).when(masterDataService).getResidentialPropertyCategoriesByZone(1);

			ResponseDTO response = MAPPER
					.readValue(
							mockMvc.perform(MockMvcRequestBuilders.get("/master-data/category?zoneId=1"))
									.andExpect(content().contentType(MediaType.APPLICATION_JSON))
									.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
							ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertNotNull(response.getResponse());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCategoriesWithoutZoneException() throws Exception {
		try {
			doThrow(new ApplicationException(ExceptionCodes.MDS_CATEGORY_NOT_FOUND)).when(masterDataService)
					.getResidentialPropertyCategories();

			ResponseDTO response = MAPPER.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/master-data/category"))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
					.getResponse().getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertTrue(errors.containsKey(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.MDS_CATEGORY_NOT_FOUND, errors.get(ApplicationConstants.CATEGORY));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetCategoriesWithZoneException() throws Exception {
		try {
			doThrow(new ApplicationException(ExceptionCodes.MDS_CATEGORY_NOT_FOUND_FOR_ZONE)).when(masterDataService)
					.getResidentialPropertyCategoriesByZone(1);

			ResponseDTO response = MAPPER
					.readValue(
							mockMvc.perform(MockMvcRequestBuilders.get("/master-data/category?zoneId=1"))
									.andExpect(content().contentType(MediaType.APPLICATION_JSON))
									.andExpect(status().isOk()).andReturn().getResponse().getContentAsString(),
							ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertTrue(errors.containsKey(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.MDS_CATEGORY_NOT_FOUND_FOR_ZONE, errors.get(ApplicationConstants.CATEGORY));
		} catch (ApplicationException e) {
			fail();
		}

	}

}
