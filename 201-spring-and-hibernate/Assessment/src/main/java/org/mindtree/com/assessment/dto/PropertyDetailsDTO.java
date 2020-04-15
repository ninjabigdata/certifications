package org.mindtree.com.assessment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.mindtree.com.assessment.constants.ExceptionCodes;

/**
 * The POJO class for property details
 * 
 * @author Balaji Sridharan
 *
 */
public class PropertyDetailsDTO {

	/**
	 * The assessment year
	 */
	@NotNull(message = ExceptionCodes.PDD_VALIDATION_NULL_ASSESSMENT_YEAR)
	@Positive(message = ExceptionCodes.PDD_VALIDATION_NEGATIVE_ASSESSMENT_YEAR)
	private Integer assessmentYear;

	/**
	 * The name of the property owner
	 */
	@NotBlank(message = ExceptionCodes.PDD_VALIDATION_NULL_OWNER)
	private String ownerName;

	/**
	 * The email id
	 */
	@NotBlank(message = ExceptionCodes.PDD_VALIDATION_NULL_EMAIL)
	private String email;

	/**
	 * The address of the property
	 */
	@NotBlank(message = ExceptionCodes.PDD_VALIDATION_NULL_ADDRESS)
	private String propertyAddress;

	/**
	 * The id of the zone master data
	 */
	@NotNull(message = ExceptionCodes.PDD_VALIDATION_NULL_ZONAL)
	private Integer zone;

	/**
	 * The id of the category master data
	 */
	@NotNull(message = ExceptionCodes.PDD_VALIDATION_NULL_CATEGORY)
	private Integer category;

	/**
	 * The id of the status master data
	 */
	@NotNull(message = ExceptionCodes.PDD_VALIDATION_NULL_STATUS)
	private Integer status;

	/**
	 * The building constructed year
	 */
	@NotNull(message = ExceptionCodes.PDD_VALIDATION_NULL_CONSTRUCTED_YEAR)
	@Positive(message = ExceptionCodes.PDD_VALIDATION_NEGATIVE_CONSTRUCTED_YEAR)
	private Integer buildingConstructedYear;

	/**
	 * The build up area of the property
	 */
	@NotNull(message = ExceptionCodes.PDD_VALIDATION_NULL_BUILD_UP_AREA)
	@Positive(message = ExceptionCodes.PDD_VALIDATION_NEGATIVE_BUILD_UP_AREA)
	private Integer buildUpArea;

	/**
	 * The total tax payable
	 */
	private double taxPayable;

	/**
	 * @return the assessmentYear
	 */
	public Integer getAssessmentYear() {
		return assessmentYear;
	}

	/**
	 * @param assessmentYear the assessmentYear to set
	 */
	public void setAssessmentYear(Integer assessmentYear) {
		this.assessmentYear = assessmentYear;
	}

	/**
	 * @return the ownerName
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * @param ownerName the ownerName to set
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the propertyAddress
	 */
	public String getPropertyAddress() {
		return propertyAddress;
	}

	/**
	 * @param propertyAddress the propertyAddress to set
	 */
	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	/**
	 * @return the zone
	 */
	public int getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(Integer zone) {
		this.zone = zone;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the buildingConstructedYear
	 */
	public int getBuildingConstructedYear() {
		return buildingConstructedYear;
	}

	/**
	 * @param buildingConstructedYear the buildingConstructedYear to set
	 */
	public void setBuildingConstructedYear(Integer buildingConstructedYear) {
		this.buildingConstructedYear = buildingConstructedYear;
	}

	/**
	 * @return the buildUpArea
	 */
	public Integer getBuildUpArea() {
		return buildUpArea;
	}

	/**
	 * @param buildUpArea the buildUpArea to set
	 */
	public void setBuildUpArea(Integer buildUpArea) {
		this.buildUpArea = buildUpArea;
	}

	/**
	 * @return the taxPayable
	 */
	public double getTaxPayable() {
		return taxPayable;
	}

	/**
	 * @param taxPayable the taxPayable to set
	 */
	public void setTaxPayable(double taxPayable) {
		this.taxPayable = taxPayable;
	}

}
