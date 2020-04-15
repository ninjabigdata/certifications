/**
 * 
 */
package org.mindtree.com.assessment.dto;

/**
 * The POJO class for master data
 * 
 * @author Balaji Sridharan
 *
 */
public class MasterDataDTO {

	/**
	 * The id of the master data value that will be used to map the master data
	 */
	private int id;

	/**
	 * The code of the master data value to be displayed in UI
	 */
	private String code;

	/**
	 * The getter method
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The setter method
	 * 
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * The getter method
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * The setter method
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
