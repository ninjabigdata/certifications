package org.mindtree.com.assessment.constants;

/**
 * The constant class for exceptions.<br />
 * <br />
 * 
 * The exception codes will be used in UI for displaying appropriate message
 * 
 * @author Balaji Sridharan
 *
 */
public class ExceptionCodes {

	private ExceptionCodes() {
		// Intentionally left blank. Since this is constant class, the object should not
		// be created outside this class.
	}

	public static final String MDS_STATUS_NOT_FOUND = "Status is not available";
	public static final String MDS_ZONE_NOT_FOUND = "Zonal classification is not available";
	public static final String MDS_CATEGORY_NOT_FOUND = "Description of the property is not available";
	public static final String MDS_CATEGORY_NOT_FOUND_FOR_ZONE = "Description of the property is not available for given zonal classification";
	public static final String PTS_VALIDATION_INVALID_EMAIL = "Email is invalid";
	public static final String PTS_VALIDATION_INVALID_ZONE = "Zonal classification is invalid";
	public static final String PTS_VALIDATION_INVALID_CATEGORY = "Description of the property is invalid";
	public static final String PTS_VALIDATION_INVALID_STATUS = "Status is invalid";
	public static final String PTS_VALIDATION_INVALID_CONSTRUCTED_YEAR_GREATER = "Building constructed year is greater than assessment year";
	public static final String PTS_VALIDATION_INVALID_ASSESSMENT_YEAR_LESS = "Assesement year is less than building constructed year";
	public static final String PTS_VALIDATION_INVALID_COMBO_FOR_TAX = "Invalid data combination of Zone Classification, Description of the Property and Status";
	public static final String PTS_VALIDATION_RECALCULATE_TAX = "Recalculate the total payable tax. Calculated tax does not with the property details provided";
	public static final String PTS_REPORT_NO_DATA = "No records available to generate report";
	public static final String PDD_VALIDATION_NULL_ASSESSMENT_YEAR = "Assessment year cannot be blank";
	public static final String PDD_VALIDATION_NEGATIVE_ASSESSMENT_YEAR = "Assessment year cannot be negative";
	public static final String PDD_VALIDATION_NULL_OWNER = "Name of the owner cannot be blank";
	public static final String PDD_VALIDATION_NULL_EMAIL = "Email cannot be blank";
	public static final String PDD_VALIDATION_NULL_ADDRESS = "Address of property cannot be blank";
	public static final String PDD_VALIDATION_NULL_ZONAL = "Zonal classification cannot be empty";
	public static final String PDD_VALIDATION_NULL_CATEGORY = "Description of the property cannot be empty";
	public static final String PDD_VALIDATION_NULL_STATUS = "Status of the property cannot be empty";
	public static final String PDD_VALIDATION_NULL_CONSTRUCTED_YEAR = "Building constructed year cannot be blank";
	public static final String PDD_VALIDATION_NEGATIVE_CONSTRUCTED_YEAR = "Building constructed year cannot be negative";
	public static final String PDD_VALIDATION_NULL_BUILD_UP_AREA = "Build up area cannot be blank";
	public static final String PDD_VALIDATION_NEGATIVE_BUILD_UP_AREA = "Build up area cannot be negative";

}
