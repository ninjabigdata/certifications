package org.mindtree.com.assessment.controller;

import static org.mindtree.com.assessment.constants.ApplicationConstants.FAILURE;
import static org.mindtree.com.assessment.constants.ApplicationConstants.SUCCESS;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.mindtree.com.assessment.constants.ApplicationConstants;
import org.mindtree.com.assessment.dto.PropertyDetailsDTO;
import org.mindtree.com.assessment.dto.ResponseDTO;
import org.mindtree.com.assessment.exception.ApplicationException;
import org.mindtree.com.assessment.service.PropertyTaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The {@link RestController} class for calculating and storing the property tax
 * 
 * @author Balaji Sridharan
 *
 */
@RestController
@RequestMapping("/property-tax")
public class PropertyTaxController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PropertyTaxService propertyTaxService;

	@PostMapping("/calculator")
	public ResponseEntity<ResponseDTO> calculatePropertyTax(@RequestBody @Valid PropertyDetailsDTO propertyDetailsDTO,
			BindingResult result) {
		ResponseDTO responseDTO = new ResponseDTO();

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (ObjectError validationError : result.getAllErrors()) {
				String code = validationError.getCodes()[0];
				errors.put(code.substring(code.lastIndexOf('.') + 1), validationError.getDefaultMessage());
			}
			responseDTO.setErrors(errors);
			responseDTO.setStatus(FAILURE);

			logger.error(errors.toString());
		} else {

			try {
				responseDTO.setResponse(propertyTaxService.calculatePropertyTax(propertyDetailsDTO));
				responseDTO.setStatus(SUCCESS);
			} catch (ApplicationException exception) {
				responseDTO.setErrors(exception.getErrors());
				responseDTO.setStatus(FAILURE);

				logger.error(exception.getMessage());
			}
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PostMapping("/payment")
	public ResponseEntity<ResponseDTO> payPropertyTax(@RequestBody @Valid PropertyDetailsDTO propertyDetailsDTO,
			BindingResult result) {
		ResponseDTO responseDTO = new ResponseDTO();

		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for (ObjectError validationError : result.getAllErrors()) {
				String code = validationError.getCodes()[0];
				errors.put(code.substring(code.lastIndexOf('.') + 1), validationError.getDefaultMessage());
			}
			responseDTO.setErrors(errors);
			responseDTO.setStatus(FAILURE);

			logger.error(errors.toString());
		} else {

			try {
				propertyTaxService.savePropertyTax(propertyDetailsDTO);
				responseDTO.setStatus(SUCCESS);
			} catch (ApplicationException exception) {
				if (exception.getExceptionCode() == null) {
					responseDTO.setErrors(exception.getErrors());
				} else {
					Map<String, String> errors = new HashMap<>();
					errors.put(ApplicationConstants.TAX_PAYABLE, exception.getExceptionCode());
					responseDTO.setErrors(errors);

					logger.error(errors.toString());
				}
				responseDTO.setStatus(FAILURE);
			}
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@GetMapping("/zonal-wise-report")
	public ResponseEntity<ResponseDTO> generateZonalWiseReport() {
		ResponseDTO responseDTO = new ResponseDTO();

		try {
			responseDTO.setResponse(propertyTaxService.generateZonalWiseReport());
			responseDTO.setStatus(SUCCESS);
		} catch (ApplicationException exception) {
			Map<String, String> errors = new HashMap<>();
			errors.put(ApplicationConstants.REPORT, exception.getExceptionCode());
			responseDTO.setErrors(errors);
			responseDTO.setStatus(FAILURE);

			logger.error(errors.toString());
		}

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

}
