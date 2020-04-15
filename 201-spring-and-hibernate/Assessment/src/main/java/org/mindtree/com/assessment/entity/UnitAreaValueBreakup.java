package org.mindtree.com.assessment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The {@link Entity} for storing the UAV details based on the property
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "unit_area_value")
public class UnitAreaValueBreakup {

	/**
	 * Auto-generated Id. Primary Key field
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	/**
	 * The category id referring to the {@link ResidentialPropertyCategory}
	 */
	@Column(name = "category_id", nullable = false)
	private Integer categoryId;

	/**
	 * The status id referring to the {@link Status}
	 */
	@Column(name = "status_id", nullable = false)
	private Integer statusId;

	/**
	 * The zone id referring to the {@link Zone}
	 */
	@Column(name = "zone_id", nullable = false)
	private Integer zoneId;

	/**
	 * The UAV
	 */
	@Column(name = "unit_area_value", nullable = false)
	private Double unitAreaValue;

	/**
	 * {@link ManyToOne} mapping.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(insertable = false, updatable = false)
	private ResidentialPropertyCategory category;

	/**
	 * {@link ManyToOne} mapping
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(insertable = false, updatable = false)
	private Status status;

	/**
	 * {@link ManyToOne} mapping
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(insertable = false, updatable = false)
	private Zone zone;

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
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * The setter method
	 * 
	 * @param categoryId
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * The getter method
	 * 
	 * @return status id
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * The setter method
	 * 
	 * @param statusId
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * The getter method
	 * 
	 * @return zone id
	 */
	public Integer getZoneId() {
		return zoneId;
	}

	/**
	 * The setter method
	 * 
	 * @param zoneId
	 */
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	/**
	 * The getter method
	 * 
	 * @return UAV
	 */
	public Double getUnitAreaValue() {
		return unitAreaValue;
	}

	/**
	 * The setter method
	 * 
	 * @param unitAreaValue
	 */
	public void setUnitAreaValue(Double unitAreaValue) {
		this.unitAreaValue = unitAreaValue;
	}

	/**
	 * The getter method
	 * 
	 * @return {@link ResidentialPropertyCategory}
	 */
	public ResidentialPropertyCategory getCategory() {
		return category;
	}

	/**
	 * The setter method
	 * 
	 * @param category
	 */
	public void setCategory(ResidentialPropertyCategory category) {
		this.category = category;
	}

	/**
	 * The getter method
	 * 
	 * @return {@link Status}
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * The setter method
	 * 
	 * @param status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * The getter method
	 * 
	 * @return {@link Zone}
	 */
	public Zone getZone() {
		return zone;
	}

	/**
	 * The setter method
	 * 
	 * @param zone
	 */
	public void setZone(Zone zone) {
		this.zone = zone;
	}

}
