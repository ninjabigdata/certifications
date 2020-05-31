package org.mindtree.com.assessment.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The {@link Entity} for master table containing the category of the buildings
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "residential_property_category")
public class ResidentialPropertyCategory implements Comparable<ResidentialPropertyCategory> {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResidentialPropertyCategory other = (ResidentialPropertyCategory) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

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
	 * The mapped {@link UnitAreaValueBreakup}
	 */
	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST }, orphanRemoval = false)
	private Set<UnitAreaValueBreakup> areaValueBreakups;

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

	/**
	 * The setter method
	 * 
	 * @return the areaValueBreakups
	 */
	public Set<UnitAreaValueBreakup> getAreaValueBreakups() {
		return areaValueBreakups;
	}

	/**
	 * The getter method
	 * 
	 * @param areaValueBreakups the areaValueBreakups to set
	 */
	public void setAreaValueBreakups(Set<UnitAreaValueBreakup> areaValueBreakups) {
		this.areaValueBreakups = areaValueBreakups;
	}

	@Override
	public String toString() {
		return "ResidentialPropertyCategory [id=" + id + ", category=" + category + ", description=" + description
				+ "]";
	}

	@Override
	public int compareTo(ResidentialPropertyCategory o) {
		return this.getId().compareTo(o.getId());
	}

}
