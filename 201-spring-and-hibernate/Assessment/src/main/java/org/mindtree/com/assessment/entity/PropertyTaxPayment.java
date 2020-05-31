package org.mindtree.com.assessment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * {@link Entity} to persist the property tax payment details. This will be used
 * for generating the report
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "property_tax_payment")
public class PropertyTaxPayment implements Comparable<PropertyTaxPayment> {

	/**
	 * Auto-generated Id. Primary Key field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	/**
	 * The year for which property tax is paid
	 */
	@Column(name = "year_of_assessment")
	private Integer assessmentYear;

	/**
	 * Name of the person who pays the tax
	 */
	@Column(name = "name_of_the_owner")
	private String ownerName;

	/**
	 * E-mail address of the person paying tax
	 */
	@Column(name = "email")
	private String email;

	/**
	 * The address of the property for which tax is paid
	 */
	@Column(name = "address_of_property")
	private String address;

	/**
	 * The foreign key for specifying the UAV details
	 */
	@Column(name = "uav_id")
	private Integer uavId;

	/**
	 * The year in which property had been constructed
	 */
	@Column(name = "building_constructed_year")
	private Integer buildingConstructedYear;

	/**
	 * The build-up area of the property
	 */
	@Column(name = "build_up_area")
	private Integer buildUpArea;

	/**
	 * Total tax paid for the property
	 */
	@Column(name = "total_tax_paid")
	private Double taxPaid;

	/**
	 * The {@link ManyToOne} mapping details
	 */
	@ManyToOne
	@JoinColumn(updatable = false, insertable = false)
	private UnitAreaValueBreakup uav;

	/**
	 * The getter method
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * The setter method
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * The getter method
	 * 
	 * @return assessmentYear
	 */
	public Integer getAssessmentYear() {
		return assessmentYear;
	}

	/**
	 * The setter method
	 * 
	 * @param assessmentYear
	 */
	public void setAssessmentYear(Integer assessmentYear) {
		this.assessmentYear = assessmentYear;
	}

	/**
	 * The getter method
	 * 
	 * @return name of the owner
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * The setter method
	 * 
	 * @param ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * The getter method
	 * 
	 * @return email address of the owner
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The setter method
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * The getter method
	 * 
	 * @return address of the property
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * The setter method
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * The getter method
	 * 
	 * @return UAV id
	 */
	public Integer getUavId() {
		return uavId;
	}

	/**
	 * The setter method
	 * 
	 * @param uavId
	 */
	public void setUavId(Integer uavId) {
		this.uavId = uavId;
	}

	/**
	 * The getter method
	 * 
	 * @return the building constructed year
	 */
	public Integer getBuildingConstructedYear() {
		return buildingConstructedYear;
	}

	/**
	 * The setter method
	 * 
	 * @param buildingConstructedYear
	 */
	public void setBuildingConstructedYear(Integer buildingConstructedYear) {
		this.buildingConstructedYear = buildingConstructedYear;
	}

	/**
	 * The getter method
	 * 
	 * @return build up area of the property
	 */
	public Integer getBuildUpArea() {
		return buildUpArea;
	}

	/**
	 * The setter method
	 * 
	 * @param buildUpArea
	 */
	public void setBuildUpArea(Integer buildUpArea) {
		this.buildUpArea = buildUpArea;
	}

	/**
	 * The getter method
	 * 
	 * @return total tax paid for the property
	 */
	public Double getTaxPaid() {
		return taxPaid;
	}

	/**
	 * The setter method
	 * 
	 * @param taxPaid
	 */
	public void setTaxPaid(Double taxPaid) {
		this.taxPaid = taxPaid;
	}

	/**
	 * The getter method
	 * 
	 * @return UAV details
	 */
	public UnitAreaValueBreakup getUav() {
		return uav;
	}

	/**
	 * The setter method
	 * 
	 * @param uav
	 */
	public void setUav(UnitAreaValueBreakup uav) {
		this.uav = uav;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((assessmentYear == null) ? 0 : assessmentYear.hashCode());
		result = prime * result + ((buildUpArea == null) ? 0 : buildUpArea.hashCode());
		result = prime * result + ((buildingConstructedYear == null) ? 0 : buildingConstructedYear.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ownerName == null) ? 0 : ownerName.hashCode());
		result = prime * result + ((taxPaid == null) ? 0 : taxPaid.hashCode());
		result = prime * result + ((uav == null) ? 0 : uav.hashCode());
		result = prime * result + ((uavId == null) ? 0 : uavId.hashCode());
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
		PropertyTaxPayment other = (PropertyTaxPayment) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (assessmentYear == null) {
			if (other.assessmentYear != null) {
				return false;
			}
		} else if (!assessmentYear.equals(other.assessmentYear)) {
			return false;
		}
		if (buildUpArea == null) {
			if (other.buildUpArea != null) {
				return false;
			}
		} else if (!buildUpArea.equals(other.buildUpArea)) {
			return false;
		}
		if (buildingConstructedYear == null) {
			if (other.buildingConstructedYear != null) {
				return false;
			}
		} else if (!buildingConstructedYear.equals(other.buildingConstructedYear)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (ownerName == null) {
			if (other.ownerName != null) {
				return false;
			}
		} else if (!ownerName.equals(other.ownerName)) {
			return false;
		}
		if (taxPaid == null) {
			if (other.taxPaid != null) {
				return false;
			}
		} else if (!taxPaid.equals(other.taxPaid)) {
			return false;
		}
		if (uav == null) {
			if (other.uav != null) {
				return false;
			}
		} else if (!uav.equals(other.uav)) {
			return false;
		}
		if (uavId == null) {
			if (other.uavId != null) {
				return false;
			}
		} else if (!uavId.equals(other.uavId)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(PropertyTaxPayment o) {
		return this.id.compareTo(o.id);
	}

}
