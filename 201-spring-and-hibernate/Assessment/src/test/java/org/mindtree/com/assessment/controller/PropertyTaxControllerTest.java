package org.mindtree.com.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mindtree.com.assessment.constants.ApplicationConstants;
import org.mindtree.com.assessment.constants.ExceptionCodes;
import org.mindtree.com.assessment.dto.PropertyDetailsDTO;
import org.mindtree.com.assessment.dto.ResponseDTO;
import org.mindtree.com.assessment.dto.ZonalWiseReportDTO;
import org.mindtree.com.assessment.exception.ApplicationException;
import org.mindtree.com.assessment.service.PropertyTaxService;
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
public class PropertyTaxControllerTest {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private MockMvc mockMvc;

	@InjectMocks
	private PropertyTaxController propertyTaxController;
	@Mock
	private PropertyTaxService propertyTaxService;

	@BeforeEach
	public void initialize() {
		mockMvc = MockMvcBuilders.standaloneSetup(propertyTaxController).build();
	}

	@Test
	public void testCalculatePropertyTaxSuccess() throws Exception {
		// Positive Flow
		PropertyDetailsDTO validPropertyDetails = new PropertyDetailsDTO();
		validPropertyDetails.setAssessmentYear(2020);
		validPropertyDetails.setOwnerName("owner");
		validPropertyDetails.setEmail("owner@own.co");
		validPropertyDetails.setPropertyAddress("address");
		validPropertyDetails.setZone(1);
		validPropertyDetails.setCategory(1);
		validPropertyDetails.setStatus(1);
		validPropertyDetails.setBuildingConstructedYear(2002);
		validPropertyDetails.setBuildUpArea(980);

		try {
			doReturn(9964.64).when(propertyTaxService).calculatePropertyTax(any(PropertyDetailsDTO.class));

			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/calculator")
							.content(MAPPER.writeValueAsString(validPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertEquals(9964.64, response.getResponse());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCalculatePropertyTaxExceptionInvalidData() throws Exception {
		PropertyDetailsDTO invalidPropertyDetails = new PropertyDetailsDTO();
		invalidPropertyDetails.setAssessmentYear(-2020);
		invalidPropertyDetails.setOwnerName("owner");
		invalidPropertyDetails.setEmail("owner@own.co");
		invalidPropertyDetails.setPropertyAddress("address");
		invalidPropertyDetails.setZone(1);
		invalidPropertyDetails.setCategory(1);
		invalidPropertyDetails.setStatus(1);
		invalidPropertyDetails.setBuildingConstructedYear(-2002);
		invalidPropertyDetails.setBuildUpArea(-980);

		try {
			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/calculator")
							.content(MAPPER.writeValueAsString(invalidPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertTrue(errors.containsKey(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NEGATIVE_ASSESSMENT_YEAR,
					errors.get(ApplicationConstants.ASSESSMENT_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NEGATIVE_CONSTRUCTED_YEAR,
					errors.get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILD_UP_AREA));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NEGATIVE_BUILD_UP_AREA,
					errors.get(ApplicationConstants.BUILD_UP_AREA));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCalculatePropertyTaxExceptionEmptyData() throws Exception {
		try {
			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/calculator").content("{}")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertEquals(9, errors.size());
			assertTrue(errors.containsKey(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_ASSESSMENT_YEAR,
					errors.get(ApplicationConstants.ASSESSMENT_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_CONSTRUCTED_YEAR,
					errors.get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILD_UP_AREA));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_BUILD_UP_AREA,
					errors.get(ApplicationConstants.BUILD_UP_AREA));
			assertTrue(errors.containsKey(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_CATEGORY, errors.get(ApplicationConstants.CATEGORY));
			assertTrue(errors.containsKey(ApplicationConstants.EMAIL));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_EMAIL, errors.get(ApplicationConstants.EMAIL));
			assertTrue(errors.containsKey(ApplicationConstants.STATUS));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_STATUS, errors.get(ApplicationConstants.STATUS));
			assertTrue(errors.containsKey(ApplicationConstants.ZONE));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_ZONAL, errors.get(ApplicationConstants.ZONE));
			assertTrue(errors.containsKey(ApplicationConstants.ADDRESS));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_ADDRESS, errors.get(ApplicationConstants.ADDRESS));
			assertTrue(errors.containsKey(ApplicationConstants.OWNER));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_OWNER, errors.get(ApplicationConstants.OWNER));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testCalculatePropertyTaxInvalidYears() throws Exception {
		PropertyDetailsDTO invalidPropertyDetails = new PropertyDetailsDTO();
		invalidPropertyDetails.setAssessmentYear(2000);
		invalidPropertyDetails.setOwnerName("owner");
		invalidPropertyDetails.setEmail("owner@own.co");
		invalidPropertyDetails.setPropertyAddress("address");
		invalidPropertyDetails.setZone(4);
		invalidPropertyDetails.setCategory(4);
		invalidPropertyDetails.setStatus(4);
		invalidPropertyDetails.setBuildingConstructedYear(2002);
		invalidPropertyDetails.setBuildUpArea(980);

		try {
			Map<String, String> actualErrors = new HashMap<>();
			actualErrors.put(ApplicationConstants.EMAIL, ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL);
			actualErrors.put(ApplicationConstants.ZONE, ExceptionCodes.PTS_VALIDATION_INVALID_ZONE);
			actualErrors.put(ApplicationConstants.CATEGORY, ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY);
			actualErrors.put(ApplicationConstants.STATUS, ExceptionCodes.PTS_VALIDATION_INVALID_STATUS);
			actualErrors.put(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR,
					ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER);
			actualErrors.put(ApplicationConstants.ASSESSMENT_YEAR,
					ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS);
			ApplicationException applicationException = new ApplicationException(actualErrors);

			doThrow(applicationException).when(propertyTaxService).calculatePropertyTax(any(PropertyDetailsDTO.class));

			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/calculator")
							.content(MAPPER.writeValueAsString(invalidPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertEquals(6, errors.size());
			assertTrue(errors.containsKey(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS,
					errors.get(ApplicationConstants.ASSESSMENT_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER,
					errors.get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY, errors.get(ApplicationConstants.CATEGORY));
			assertTrue(errors.containsKey(ApplicationConstants.EMAIL));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL, errors.get(ApplicationConstants.EMAIL));
			assertTrue(errors.containsKey(ApplicationConstants.STATUS));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_STATUS, errors.get(ApplicationConstants.STATUS));
			assertTrue(errors.containsKey(ApplicationConstants.ZONE));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_ZONE, errors.get(ApplicationConstants.ZONE));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@Test
	public void testPayPropertyTaxSuccess() throws Exception {
		PropertyDetailsDTO validPropertyDetails = new PropertyDetailsDTO();
		validPropertyDetails.setAssessmentYear(2020);
		validPropertyDetails.setOwnerName("owner");
		validPropertyDetails.setEmail("owner@own.co");
		validPropertyDetails.setPropertyAddress("address");
		validPropertyDetails.setZone(1);
		validPropertyDetails.setCategory(1);
		validPropertyDetails.setStatus(1);
		validPropertyDetails.setBuildingConstructedYear(2002);
		validPropertyDetails.setBuildUpArea(980);
		validPropertyDetails.setTaxPayable(9964.64);

		try {
			doNothing().when(propertyTaxService).savePropertyTax(any(PropertyDetailsDTO.class));

			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/payment")
							.content(MAPPER.writeValueAsString(validPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPayPropertyTaxExceptionInvalidData() throws Exception {
		PropertyDetailsDTO invalidPropertyDetails = new PropertyDetailsDTO();
		invalidPropertyDetails.setAssessmentYear(-2020);
		invalidPropertyDetails.setOwnerName("owner");
		invalidPropertyDetails.setEmail("owner@own.co");
		invalidPropertyDetails.setPropertyAddress("address");
		invalidPropertyDetails.setZone(1);
		invalidPropertyDetails.setCategory(1);
		invalidPropertyDetails.setStatus(1);
		invalidPropertyDetails.setBuildingConstructedYear(-2002);
		invalidPropertyDetails.setBuildUpArea(-980);

		try {
			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/payment")
							.content(MAPPER.writeValueAsString(invalidPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertTrue(errors.containsKey(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NEGATIVE_ASSESSMENT_YEAR,
					errors.get(ApplicationConstants.ASSESSMENT_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NEGATIVE_CONSTRUCTED_YEAR,
					errors.get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILD_UP_AREA));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NEGATIVE_BUILD_UP_AREA,
					errors.get(ApplicationConstants.BUILD_UP_AREA));
		} catch (ApplicationException e) {
			fail();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPayPropertyTaxExceptionEmptyData() throws Exception {
		try {
			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/payment").content("{}")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertEquals(9, errors.size());
			assertTrue(errors.containsKey(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_ASSESSMENT_YEAR,
					errors.get(ApplicationConstants.ASSESSMENT_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_CONSTRUCTED_YEAR,
					errors.get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILD_UP_AREA));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_BUILD_UP_AREA,
					errors.get(ApplicationConstants.BUILD_UP_AREA));
			assertTrue(errors.containsKey(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_CATEGORY, errors.get(ApplicationConstants.CATEGORY));
			assertTrue(errors.containsKey(ApplicationConstants.EMAIL));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_EMAIL, errors.get(ApplicationConstants.EMAIL));
			assertTrue(errors.containsKey(ApplicationConstants.STATUS));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_STATUS, errors.get(ApplicationConstants.STATUS));
			assertTrue(errors.containsKey(ApplicationConstants.ZONE));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_ZONAL, errors.get(ApplicationConstants.ZONE));
			assertTrue(errors.containsKey(ApplicationConstants.ADDRESS));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_ADDRESS, errors.get(ApplicationConstants.ADDRESS));
			assertTrue(errors.containsKey(ApplicationConstants.OWNER));
			assertEquals(ExceptionCodes.PDD_VALIDATION_NULL_OWNER, errors.get(ApplicationConstants.OWNER));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPayPropertyTaxExceptionInvalidYears() throws Exception {
		PropertyDetailsDTO invalidPropertyDetails = new PropertyDetailsDTO();
		invalidPropertyDetails.setAssessmentYear(2000);
		invalidPropertyDetails.setOwnerName("owner");
		invalidPropertyDetails.setEmail("owner@own.co");
		invalidPropertyDetails.setPropertyAddress("address");
		invalidPropertyDetails.setZone(4);
		invalidPropertyDetails.setCategory(4);
		invalidPropertyDetails.setStatus(4);
		invalidPropertyDetails.setBuildingConstructedYear(2002);
		invalidPropertyDetails.setBuildUpArea(980);

		try {
			Map<String, String> actualErrors = new HashMap<>();
			actualErrors.put(ApplicationConstants.EMAIL, ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL);
			actualErrors.put(ApplicationConstants.ZONE, ExceptionCodes.PTS_VALIDATION_INVALID_ZONE);
			actualErrors.put(ApplicationConstants.CATEGORY, ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY);
			actualErrors.put(ApplicationConstants.STATUS, ExceptionCodes.PTS_VALIDATION_INVALID_STATUS);
			actualErrors.put(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR,
					ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER);
			actualErrors.put(ApplicationConstants.ASSESSMENT_YEAR,
					ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS);
			ApplicationException applicationException = new ApplicationException(actualErrors);

			doThrow(applicationException).when(propertyTaxService).savePropertyTax(any(PropertyDetailsDTO.class));

			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/payment")
							.content(MAPPER.writeValueAsString(invalidPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertEquals(6, errors.size());
			assertTrue(errors.containsKey(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS,
					errors.get(ApplicationConstants.ASSESSMENT_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER,
					errors.get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
			assertTrue(errors.containsKey(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY, errors.get(ApplicationConstants.CATEGORY));
			assertTrue(errors.containsKey(ApplicationConstants.EMAIL));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL, errors.get(ApplicationConstants.EMAIL));
			assertTrue(errors.containsKey(ApplicationConstants.STATUS));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_STATUS, errors.get(ApplicationConstants.STATUS));
			assertTrue(errors.containsKey(ApplicationConstants.ZONE));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_ZONE, errors.get(ApplicationConstants.ZONE));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testPayPropertyTaxExceptionInvalidTax() throws Exception {
		PropertyDetailsDTO invalidPropertyDetails = new PropertyDetailsDTO();
		invalidPropertyDetails.setAssessmentYear(2020);
		invalidPropertyDetails.setOwnerName("owner");
		invalidPropertyDetails.setEmail("owner@own.co");
		invalidPropertyDetails.setPropertyAddress("address");
		invalidPropertyDetails.setZone(1);
		invalidPropertyDetails.setCategory(1);
		invalidPropertyDetails.setStatus(1);
		invalidPropertyDetails.setBuildingConstructedYear(2002);
		invalidPropertyDetails.setBuildUpArea(980);
		invalidPropertyDetails.setTaxPayable(9964.6);

		try {
			doThrow(new ApplicationException(ExceptionCodes.PTS_VALIDATION_RECALCULATE_TAX)).when(propertyTaxService)
					.savePropertyTax(any(PropertyDetailsDTO.class));

			ResponseDTO response = MAPPER.readValue(mockMvc
					.perform(MockMvcRequestBuilders.post("/property-tax/payment")
							.content(MAPPER.writeValueAsString(invalidPropertyDetails))
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
					.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertEquals(1, errors.size());
			assertTrue(errors.containsKey(ApplicationConstants.TAX_PAYABLE));
			assertEquals(ExceptionCodes.PTS_VALIDATION_RECALCULATE_TAX, errors.get(ApplicationConstants.TAX_PAYABLE));
		} catch (ApplicationException e) {
			fail();
		}

	}

	@Test
	public void testGenerateZonalWiseReportSuccess() throws Exception {
		try {
			List<ZonalWiseReportDTO> zonalWiseReportDTOs = new ArrayList<>();
			ZonalWiseReportDTO zonalWiseReportDTO = new ZonalWiseReportDTO();
			zonalWiseReportDTO.setZoneName("Zone A");
			Map<String, Double> amountCollectedByPropertyType = new HashMap<>();
			amountCollectedByPropertyType.put("Owner", 9870797.98);
			amountCollectedByPropertyType.put("Tenanted", 870797.98);
			zonalWiseReportDTO.setAmountCollectedByPropertyType(amountCollectedByPropertyType);
			doReturn(zonalWiseReportDTOs).when(propertyTaxService).generateZonalWiseReport();

			ResponseDTO response = MAPPER
					.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/property-tax/zonal-wise-report"))
							.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
							.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.SUCCESS, response.getStatus());
			assertNotNull(response.getResponse());
			assertNull(response.getErrors());
		} catch (ApplicationException e) {
			fail();
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGenerateZonalWiseReportException() throws Exception {
		try {
			doThrow(new ApplicationException(ExceptionCodes.PTS_REPORT_NO_DATA)).when(propertyTaxService)
					.generateZonalWiseReport();

			ResponseDTO response = MAPPER
					.readValue(mockMvc.perform(MockMvcRequestBuilders.get("/property-tax/zonal-wise-report"))
							.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse()
							.getContentAsString(), ResponseDTO.class);

			assertEquals(ApplicationConstants.FAILURE, response.getStatus());
			assertNull(response.getResponse());
			assertNotNull(response.getErrors());
			Map<String, String> errors = (Map<String, String>) response.getErrors();
			assertEquals(1, errors.size());
			assertTrue(errors.containsKey(ApplicationConstants.REPORT));
			assertEquals(ExceptionCodes.PTS_REPORT_NO_DATA, errors.get(ApplicationConstants.REPORT));
		} catch (ApplicationException e) {
			fail();
		}

	}

}
