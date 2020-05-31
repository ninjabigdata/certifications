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
 * The {@link Entity} for master table containing the status of the property
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "status")
public class Status implements Comparable<Status> {

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Status other = (Status) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Status o) {
		return this.getId().compareTo(o.getId());
	}

}
