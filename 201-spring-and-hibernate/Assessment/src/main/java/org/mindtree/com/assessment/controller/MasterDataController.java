package org.mindtree.com.assessment.controller;

import static org.mindtree.com.assessment.constants.ApplicationConstants.FAILURE;
import static org.mindtree.com.assessment.constants.ApplicationConstants.SUCCESS;

import java.util.HashMap;
import java.util.Map;

import org.mindtree.com.assessment.constants.ApplicationConstants;
import org.mindtree.com.assessment.dto.ResponseDTO;
import org.mindtree.com.assessment.exception.ApplicationException;
import org.mindtree.com.assessment.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@link RestController} class for handling the Master Data
 * 
 * @author Balaji Sridharan
 *
 */
@RestController
@RequestMapping("/master-data")
public class MasterDataController {

	@Autowired
	private MasterDataService masterDataService;

	/**
	 * The {@link GetMapping} to fetch the zones
	 * 
	 * @return the {@link ResponseEntity} of {@link ResponseDTO}
	 */
	@GetMapping("/zone")
	public ResponseEntity<ResponseDTO> getZones() {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setResponse(masterDataService.getZones());
			responseDTO.setStatus(SUCCESS);
		} catch (ApplicationException exception) {
			Map<String, String> errors = new HashMap<>();
			errors.put(ApplicationConstants.ZONE, exception.getExceptionCode());

			responseDTO.setErrors(errors);
			responseDTO.setStatus(FAILURE);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * The {@link GetMapping} to fetch the statuses
	 * 
	 * @return the {@link ResponseEntity} of {@link ResponseDTO}
	 */
	@GetMapping("/status")
	public ResponseEntity<ResponseDTO> getStatuses() {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setResponse(masterDataService.getStatuses());
			responseDTO.setStatus(SUCCESS);
		} catch (ApplicationException exception) {
			Map<String, String> errors = new HashMap<>();
			errors.put(ApplicationConstants.STATUS, exception.getExceptionCode());

			responseDTO.setErrors(errors);
			responseDTO.setStatus(FAILURE);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * The {@link GetMapping} to fetch the Residential Property Categories. The
	 * {@link RequestParam} zoneId is optional. If zoneId is provided, residential
	 * property categories will be fetched based on the zone id.
	 * 
	 * @param zoneId the optional {@link RequestParam}
	 * @return the {@link ResponseEntity} of {@link ResponseDTO}
	 */
	@GetMapping("/category")
	public ResponseEntity<ResponseDTO> getCategory(@RequestParam(name = "zoneId", required = false) Integer zoneId) {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setResponse(zoneId == null ? masterDataService.getResidentialPropertyCategories()
					: masterDataService.getResidentialPropertyCategoriesByZone(zoneId));
			responseDTO.setStatus(SUCCESS);
		} catch (ApplicationException exception) {
			Map<String, String> errors = new HashMap<>();
			errors.put(ApplicationConstants.CATEGORY, exception.getExceptionCode());

			responseDTO.setErrors(errors);
			responseDTO.setStatus(FAILURE);
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

}
