package org.mindtree.com.assessment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.mindtree.com.assessment.service.PropertyTaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The implementation class of {@link PropertyTaxService}
 * 
 * @author Balaji Sridharan
 *
 */
@Service
public class PropertyTaxServiceImpl implements PropertyTaxService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UnitAreaValueRespository unitAreaValueRespository;
	@Autowired
	private ResidentialPropertyCategoryRepository categoryRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private PropertyTaxPaymentRepository taxPaymentRepository;

	@Override
	public Double calculatePropertyTax(PropertyDetailsDTO propertyDetailsDTO) throws ApplicationException {
		Map<String, String> errors = new HashMap<>();

		// Validate email
		if (!propertyDetailsDTO.getEmail().matches(ApplicationConstants.EMAIL_PATTERN)) {
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL);

			errors.put(ApplicationConstants.EMAIL, ExceptionCodes.PTS_VALIDATION_INVALID_EMAIL);
		}

		// valid zone id
		if (!zoneRepository.existsById(propertyDetailsDTO.getZone())) {
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_ZONE);

			errors.put(ApplicationConstants.ZONE, ExceptionCodes.PTS_VALIDATION_INVALID_ZONE);
		}

		// valid category id
		if (!categoryRepository.existsById(propertyDetailsDTO.getCategory())) {
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY);

			errors.put(ApplicationConstants.CATEGORY, ExceptionCodes.PTS_VALIDATION_INVALID_CATEGORY);
		}

		// valid status id
		if (!statusRepository.existsById(propertyDetailsDTO.getStatus())) {
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_STATUS);

			errors.put(ApplicationConstants.STATUS, ExceptionCodes.PTS_VALIDATION_INVALID_STATUS);
		}

		// Calculate Applicable Depreciation
		int buildingAge = propertyDetailsDTO.getAssessmentYear() - propertyDetailsDTO.getBuildingConstructedYear();

		if (buildingAge < 0) {
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER);
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS);

			errors.put(ApplicationConstants.BUILDING_CONSTRUCTED_YEAR,
					ExceptionCodes.PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER);
			errors.put(ApplicationConstants.ASSESSMENT_YEAR,
					ExceptionCodes.PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS);
		} else if (buildingAge > 60) {
			buildingAge = 60;
		}

		if (!errors.isEmpty()) {
			logger.error(errors.toString());

			throw new ApplicationException(errors);
		}

		// GET UAV
		UnitAreaValueBreakup uav = unitAreaValueRespository.findByCategoryIdAndZoneIdAndStatusId(
				propertyDetailsDTO.getCategory(), propertyDetailsDTO.getZone(), propertyDetailsDTO.getStatus());

		if (uav == null) {
			logger.error(ExceptionCodes.PTS_VALIDATION_INVALID_COMBO_FOR_TAX);

			errors.put(ApplicationConstants.TAX_PAYABLE, ExceptionCodes.PTS_VALIDATION_INVALID_COMBO_FOR_TAX);

			throw new ApplicationException(errors);
		}

		// Calculating the Total Payable Tax
		double total1 = propertyDetailsDTO.getBuildUpArea() * uav.getUnitAreaValue() * 10;

		double total2 = total1 - (total1 * buildingAge / 100.0);

		double total3 = total2 * (0.2);

		double total4 = total3 * (0.24);

		return Math.round((total3 + total4) * 100.0) / 100.0;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Override
	public void savePropertyTax(PropertyDetailsDTO propertyDetailsDTO) throws ApplicationException {
		Double updatedTax = calculatePropertyTax(propertyDetailsDTO);

		if (updatedTax.compareTo(propertyDetailsDTO.getTaxPayable()) != 0) {
			logger.error(ExceptionCodes.PTS_VALIDATION_RECALCULATE_TAX);

			throw new ApplicationException(ExceptionCodes.PTS_VALIDATION_RECALCULATE_TAX);
		}

		PropertyTaxPayment propertyTaxPayment = new PropertyTaxPayment();
		propertyTaxPayment.setAssessmentYear(propertyDetailsDTO.getAssessmentYear());
		propertyTaxPayment.setOwnerName(propertyDetailsDTO.getOwnerName());
		propertyTaxPayment.setEmail(propertyDetailsDTO.getEmail());
		propertyTaxPayment.setAddress(propertyDetailsDTO.getPropertyAddress());
		propertyTaxPayment.setUavId(
				unitAreaValueRespository.findByCategoryIdAndZoneIdAndStatusId(propertyDetailsDTO.getCategory(),
						propertyDetailsDTO.getZone(), propertyDetailsDTO.getStatus()).getId());
		propertyTaxPayment.setBuildingConstructedYear(propertyDetailsDTO.getBuildingConstructedYear());
		propertyTaxPayment.setBuildUpArea(propertyDetailsDTO.getBuildUpArea());
		propertyTaxPayment.setTaxPaid(propertyDetailsDTO.getTaxPayable());

		taxPaymentRepository.saveAndFlush(propertyTaxPayment);

	}

	@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
	@Override
	public List<ZonalWiseReportDTO> generateZonalWiseReport() throws ApplicationException {
		List<ZonalWiseReportDTO> report = new ArrayList<>();

		List<ZonalWiseReport> zonalWiseReport = taxPaymentRepository.getZoneWiseReport();

		if (zonalWiseReport.isEmpty()) {
			logger.error(ExceptionCodes.PTS_REPORT_NO_DATA);

			throw new ApplicationException(ExceptionCodes.PTS_REPORT_NO_DATA);
		}

		zonalWiseReport.stream().forEach(zonalReport -> {
			ZonalWiseReportDTO zonalWiseReportDTO = null;
			for (ZonalWiseReportDTO dto : report) {
				if (dto.getZoneName().equals(zonalReport.getZone_name())) {
					zonalWiseReportDTO = dto;
					break;
				}
			}

			if (zonalWiseReportDTO == null) {
				zonalWiseReportDTO = new ZonalWiseReportDTO();
				zonalWiseReportDTO.setZoneName(zonalReport.getZone_name());
				zonalWiseReportDTO.setAmountCollectedByPropertyType(new LinkedHashMap<>());
				report.add(zonalWiseReportDTO);
			}

			zonalWiseReportDTO.getAmountCollectedByPropertyType().put(zonalReport.getProperty_type(),
					zonalReport.getAmount_collected());

		});

		return report;
	}

}
