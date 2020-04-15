package org.mindtree.com.assessment.dto;

/**
 * The POJO class for REST API responses
 * 
 * @author Balaji Sridharan
 *
 */
public class ResponseDTO {

	/**
	 * The status of the REST API. Either success or failure
	 * 
	 */
	private String status;

	/**
	 * The actual response data, if status is success
	 */
	private Object response;

	/**
	 * The errors, if status is failure
	 */
	private Object errors;

	/**
	 * The getter method
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * The setter method
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * The getter method
	 * 
	 * @return the response
	 */
	public Object getResponse() {
		return response;
	}

	/**
	 * The setter method
	 * 
	 * @param response the response to set
	 */
	public void setResponse(Object response) {
		this.response = response;
	}

	/**
	 * The getter method
	 * 
	 * @return the errors
	 */
	public Object getErrors() {
		return errors;
	}

	/**
	 * The setter method
	 * 
	 * @param errors the errors to set
	 */
	public void setErrors(Object errors) {
		this.errors = errors;
	}

}
