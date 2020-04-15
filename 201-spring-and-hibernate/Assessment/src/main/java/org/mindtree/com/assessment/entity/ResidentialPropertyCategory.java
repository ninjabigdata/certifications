package org.mindtree.com.assessment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The {@link Entity} for master table containing the category of the buildings
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "residential_property_category")
public class ResidentialPropertyCategory {

	/**
	 * Auto-generated Id. Primary Key field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/**
	 * The category Id
	 */
	@Column(name = "category")
	private String category;

	/**
	 * The description of the category
	 */
	@Column(name = "description")
	private String description;

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
	 * @return category id
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * The setter method
	 * 
	 * @param category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * The getter method
	 * 
	 * @return description of the category
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * The setter method
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
