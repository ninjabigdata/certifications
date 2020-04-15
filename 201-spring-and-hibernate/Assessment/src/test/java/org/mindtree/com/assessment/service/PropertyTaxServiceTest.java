package org.mindtree.com.assessment.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mindtree.com.assessment.constants.ApplicationConstants;
import org.mindtree.com.assessment.constants.ExceptionCodes;
import org.mindtree.com.assessment.dto.PropertyDetailsDTO;
import org.mindtree.com.assessment.dto.ZonalWiseReportDTO;
import org.mindtree.com.assessment.entity.PropertyTaxPayment;
import org.mindtree.com.assessment.entity.UnitAreaValueBreakup;
import org.mindtree.com.assessment.exception.ApplicationException;
import org.mindtree.com.assessment.repository.PropertyTaxPaymentRepository;
import org.mindtree.com.assessment.repository.ResidentialPropertyCategoryRepository;
import org.mindtree.com.assessment.repository.StatusRepository;
import org.mindtree.com.assessment.repository.UnitAreaValueRespository;
import org.mindtree.com.assessment.repository.ZoneRepository;
import org.mindtree.com.assessment.repository.projection.ZonalWiseReport;
import org.mindtree.com.assessment.service.impl.PropertyTaxServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PropertyTaxServiceTest {

	@InjectMocks
	@Spy
	private PropertyTaxServiceImpl propertyTaxServiceImpl;
	@Mock
	private StatusRepository statusRepository;
	@Mock
	private ZoneRepository zoneRepository;
	@Mock
	private ResidentialPropertyCategoryRepository categoryRepository;
	@Mock
	private UnitAreaValueRespository uavRespository;
	@Mock
	private PropertyTaxPaymentRepository taxPaymentRepository;

	@Test
	public void testCalculatePropertyTax() {
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

		UnitAreaValueBreakup uav = new UnitAreaValueBreakup();
		uav.setUnitAreaValue(5.00);

		doReturn(true).when(zoneRepository).existsById(1);
		doReturn(true).when(categoryRepository).existsById(1);
		doReturn(true).when(statusRepository).existsById(1);
		doReturn(uav).when(uavRespository).findByCategoryIdAndZoneIdAndStatusId(1, 1, 1);

		try {
			assertNotNull(propertyTaxServiceImpl.calculatePropertyTax(validPropertyDetails));
		} catch (ApplicationException exception) {
			fail();
		}
		
		// For applicable appreciation, age greater than 60 years
		validPropertyDetails.setBuildingConstructedYear(1959);

		try {
			assertNotNull(propertyTaxServiceImpl.calculatePropertyTax(validPropertyDetails));
		} catch (ApplicationException exception) {
			fail();
		}

		// Exception Flows
		// Invalid Combination to get UAV
		doReturn(null).when(uavRespository).findByCategoryIdAndZoneIdAndStatusId(1, 1, 1);

		try {
			propertyTaxServiceImpl.calculatePropertyTax(validPropertyDetails);
		} catch (ApplicationException exception) {
			assertEquals(1, exception.getErrors().size());
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_COMBO_FOR_TAX,
					exception.getErrors().get(ApplicationConstants.TAX_PAYABLE));
		}

		// Other validation errors
		PropertyDetailsDTO invalidPropertyDetails = new PropertyDetailsDTO();
		invalidPropertyDetails.setAssessmentYear(2000);
		invalidPropertyDetails.setOwnerName("owner");
		invalidPropertyDetails.setEmail("ownerown.co");
		invalidPropertyDetails.setPropertyAddress("address");
		invalidPropertyDetails.setZone(8);
		invalidPropertyDetails.setCategory(5);
		invalidPropertyDetails.setStatus(7);
		invalidPropertyDetails.setBuildingConstructedYear(2002);
		invalidPropertyDetails.setBuildUpArea(980);

		doReturn(false).when(zoneRepository).existsById(8);
		doReturn(false).when(categoryRepository).existsById(5);
		doReturn(false).when(statusRepository).existsById(7);

		try {
			propertyTaxServiceImpl.calculatePropertyTax(invalidPropertyDetails);
		} catch (ApplicationException exception) {
			assertEquals(6, exception.getErrors().size());
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL,
					exception.getErrors().get(ApplicationConstants.EMAIL));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_ZONE,
					exception.getErrors().get(ApplicationConstants.ZONE));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY,
					exception.getErrors().get(ApplicationConstants.CATEGORY));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_STATUS,
					exception.getErrors().get(ApplicationConstants.STATUS));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS,
					exception.getErrors().get(ApplicationConstants.ASSESSMENT_YEAR));
			assertEquals(ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER,
					exception.getErrors().get(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR));
		}

	}

	@Test
	public void testSavePropertyTax() {
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
		validPropertyDetails.setTaxPayable(9964.64);

		UnitAreaValueBreakup uav = new UnitAreaValueBreakup();
		uav.setUnitAreaValue(5.00);

		doReturn(uav).when(uavRespository).findByCategoryIdAndZoneIdAndStatusId(1, 1, 1);
		doReturn(mock(PropertyTaxPayment.class)).when(taxPaymentRepository).saveAndFlush(any(PropertyTaxPayment.class));

		try {
			doReturn(9964.64).when(propertyTaxServiceImpl).calculatePropertyTax(validPropertyDetails);

			propertyTaxServiceImpl.savePropertyTax(validPropertyDetails);
		} catch (ApplicationException exception) {
			fail();
		}

		// Exception Flow
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
		invalidPropertyDetails.setTaxPayable(9964.0);

		try {
			doReturn(9964.64).when(propertyTaxServiceImpl).calculatePropertyTax(invalidPropertyDetails);

			propertyTaxServiceImpl.savePropertyTax(invalidPropertyDetails);
		} catch (ApplicationException exception) {
			assertEquals(ExceptionCodes.PTS_VALIDATION_RECALCULATE_TAX, exception.getExceptionCode());
		}

	}

	@Test
	public void testGenerateZonalWiseReport() {
		// Positive Flow
		List<ZonalWiseReport> zonalWiseReports = new ArrayList<>();
		zonalWiseReports.add(new ZonalWiseReport() {

			@Override
			public String getZone_name() {
				return "Zone A";
			}

			@Override
			public String getProperty_type() {
				return "Owner";
			}

			@Override
			public Double getAmount_collected() {
				return 47337.01;
			}
		});
		zonalWiseReports.add(new ZonalWiseReport() {

			@Override
			public String getZone_name() {
				return "Zone A";
			}

			@Override
			public String getProperty_type() {
				return "Tenanted";
			}

			@Override
			public Double getAmount_collected() {
				return 126232.00;
			}
		});
		zonalWiseReports.add(new ZonalWiseReport() {

			@Override
			public String getZone_name() {
				return "Zone B";
			}

			@Override
			public String getProperty_type() {
				return "Owner";
			}

			@Override
			public Double getAmount_collected() {
				return 58066.73;
			}
		});

		doReturn(zonalWiseReports).when(taxPaymentRepository).getZoneWiseReport();

		try {
			List<ZonalWiseReportDTO> actualReport = propertyTaxServiceImpl.generateZonalWiseReport();

			assertEquals(2, actualReport.size());
			assertEquals("Zone A", actualReport.get(0).getZoneName());
			assertTrue(actualReport.get(0).getAmountCollectedByPropertyType().containsKey("Owner"));
			assertEquals(47337.01, actualReport.get(0).getAmountCollectedByPropertyType().get("Owner"));
			assertTrue(actualReport.get(0).getAmountCollectedByPropertyType().containsKey("Tenanted"));
			assertEquals(126232.00, actualReport.get(0).getAmountCollectedByPropertyType().get("Tenanted"));
			assertEquals("Zone B", actualReport.get(1).getZoneName());
			assertTrue(actualReport.get(1).getAmountCollectedByPropertyType().containsKey("Owner"));
			assertEquals(58066.73, actualReport.get(1).getAmountCollectedByPropertyType().get("Owner"));
		} catch (ApplicationException exception) {
			fail();
		}

		// Exception Flow
		doReturn(Collections.EMPTY_LIST).when(taxPaymentRepository).getZoneWiseReport();

		try {
			propertyTaxServiceImpl.generateZonalWiseReport();
		} catch (ApplicationException exception) {
			assertEquals(ExceptionCodes.PTS_REPORT_NO_DATA, exception.getExceptionCode());
		}

	}

}
