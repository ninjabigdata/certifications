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
public class PropertyTaxPayment {

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

}
