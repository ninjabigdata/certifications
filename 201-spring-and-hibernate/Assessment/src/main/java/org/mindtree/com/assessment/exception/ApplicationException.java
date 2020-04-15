package org.mindtree.com.assessment.exception;

import java.util.Map;

import javassist.SerialVersionUID;

/**
 * The custom exception class
 * 
 * @author Balaji Sridharan
 *
 */
public class ApplicationException extends Exception {

	/**
	 * The {@link SerialVersionUID}
	 */
	private static final long serialVersionUID = -1550619823270746719L;
	/**
	 * The exception code
	 */
	private final String exceptionCode;

	private final Map<String, String> errors;

	/**
	 * The parameterized constructor
	 * 
	 * @param exceptionCode the code for the exception
	 */
	public ApplicationException(String exceptionCode) {
		super();
		this.exceptionCode = exceptionCode;
		this.errors = null;
	}

	/**
	 * The parameterized constructor
	 * 
	 * @param errors the errors
	 */
	public ApplicationException(Map<String, String> errors) {
		super();
		this.errors = errors;
		this.exceptionCode = null;
	}

	/**
	 * The parameterized constructor
	 * 
	 * @param exceptionCode the code for the exception
	 * @param throwable     the {@link Throwable}
	 */
	public ApplicationException(String exceptionCode, Throwable throwable) {
		super();
		this.exceptionCode = exceptionCode;
		this.errors = null;
	}

	/**
	 * The getter method
	 * 
	 * @return the exceptionCode
	 */
	public String getExceptionCode() {
		return exceptionCode;
	}

	/**
	 * @return the errors
	 */
	public Map<String, String> getErrors() {
		return errors;
	}

}
