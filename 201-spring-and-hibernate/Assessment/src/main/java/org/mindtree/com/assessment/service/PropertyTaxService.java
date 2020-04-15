package org.mindtree.com.assessment.service;

import java.util.List;

import org.mindtree.com.assessment.dto.PropertyDetailsDTO;
import org.mindtree.com.assessment.dto.ZonalWiseReportDTO;
import org.mindtree.com.assessment.exception.ApplicationException;

/**
 * Service interface for storing and calculating property tax
 * 
 * @author Balaji Sridharan
 *
 */
public interface PropertyTaxService {

	/**
	 * Computes the total tax payable based on the details provided in the input
	 * 
	 * @param propertyDetailsDTO the details for calculating the tax
	 * @return the calculated tax
	 * @throws ApplicationException the exception thrown if error in calculating
	 *                              property tax
	 */
	Double calculatePropertyTax(PropertyDetailsDTO propertyDetailsDTO) throws ApplicationException;

	/**
	 * Saves the computed total tax payable and other details provided in the input
	 * 
	 * @param propertyDetailsDTO the details of the tax
	 * @throws ApplicationException the exception thrown if error in calculating
	 *                              property tax
	 */
	void savePropertyTax(PropertyDetailsDTO propertyDetailsDTO) throws ApplicationException;

	/**
	 * Generated the summation of property tax paid grouped by zonal classification
	 * and property type
	 * 
	 * @return the {@link List} of {@link ZonalWiseReportDTO}
	 * @throws ApplicationException the exception thrown if error in generating
	 *                              report
	 */
	List<ZonalWiseReportDTO> generateZonalWiseReport() throws ApplicationException;

}
