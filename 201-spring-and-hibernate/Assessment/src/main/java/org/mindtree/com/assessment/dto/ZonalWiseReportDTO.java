package org.mindtree.com.assessment.dto;

import java.util.Map;

/**
 * The POJO class for zonal wise report for the current year
 * 
 * @author Balaji Sridharan
 *
 */
public class ZonalWiseReportDTO {

	/**
	 * The zonal classification name
	 */
	private String zoneName;

	/**
	 * The sum of the property tax collected grouped by property types in the zonal
	 * classification
	 */
	private Map<String, Double> amountCollectedByPropertyType;

	/**
	 * The getter method
	 * 
	 * @return the zoneName
	 */
	public String getZoneName() {
		return zoneName;
	}

	/**
	 * The setter method
	 * 
	 * @param zoneName the zoneName to set
	 */
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	/**
	 * The getter method
	 * 
	 * @return the amountCollectedByPropertyType
	 */
	public Map<String, Double> getAmountCollectedByPropertyType() {
		return amountCollectedByPropertyType;
	}

	/**
	 * The setter method
	 * 
	 * @param amountCollectedByPropertyType the amountCollectedByPropertyType to set
	 */
	public void setAmountCollectedByPropertyType(Map<String, Double> amountCollectedByPropertyType) {
		this.amountCollectedByPropertyType = amountCollectedByPropertyType;
	}

}
