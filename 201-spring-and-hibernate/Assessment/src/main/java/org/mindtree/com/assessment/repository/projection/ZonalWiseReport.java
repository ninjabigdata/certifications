package org.mindtree.com.assessment.repository.projection;

import org.mindtree.com.assessment.entity.Status;
import org.mindtree.com.assessment.entity.Zone;

/**
 * The interface for projection of Zonal Wise Report
 * 
 * @author Balaji Sridharan
 *
 */
public interface ZonalWiseReport {

	/**
	 * The property type. <br />
	 * <br />
	 * Refer {@link Status} for property types
	 * 
	 * @return the property type
	 */
	String getProperty_type();

	/**
	 * The zonal classification name. <br />
	 * <br />
	 * Refer {@link Zone} for zonal classification
	 * 
	 * @return the zonal classification name
	 */
	String getZone_name();

	/**
	 * The sum of tax collected for the current year categorized based on zonal
	 * classification and property type
	 * 
	 * @return the sum of tax collected
	 */
	Double getAmount_collected();

}
