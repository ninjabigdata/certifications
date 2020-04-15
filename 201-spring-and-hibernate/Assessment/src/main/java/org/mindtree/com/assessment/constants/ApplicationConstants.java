package org.mindtree.com.assessment.constants;

/**
 * The constant class for storing the application constants
 * 
 * @author basridha
 *
 */
public class ApplicationConstants {

	private ApplicationConstants() {
		// Object should not be created
	}

	// For REST API Response
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";

	// REGEX
	public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	// Field Names
	public static final String EMAIL = "email";
	public static final String ZONE = "zone";
	public static final String CATEGORY = "category";
	public static final String STATUS = "status";
	public static final String BUILDING_CONSTRUCTED_YEAR = "buildingConstructedYear";
	public static final String ASSESSMENT_YEAR = "assessmentYear";
	public static final String TAX_PAYABLE = "taxPayable";
	public static final String REPORT = "report";
	public static final String BUILD_UP_AREA = "buildUpArea";
	public static final String OWNER = "ownerName";
	public static final String ADDRESS = "propertyAddress";

}
