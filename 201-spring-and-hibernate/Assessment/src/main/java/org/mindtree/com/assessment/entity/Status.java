package org.mindtree.com.assessment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The {@link Entity} for master table containing the status of the property
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "status")
public class Status {

	/**
	 * Auto-generated Id. Primary Key field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/**
	 * The name of the status to be displayed in UI
	 */
	@Column
	private String name;

	/**
	 * The getter method
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * The setter method
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * The getter method
	 * 
	 * @return name of the status
	 */
	public String getName() {
		return name;
	}

	/**
	 * The setter method
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
