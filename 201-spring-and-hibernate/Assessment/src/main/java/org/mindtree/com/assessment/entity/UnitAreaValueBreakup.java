package org.mindtree.com.assessment.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The {@link Entity} for storing the UAV details based on the property
 * 
 * @author Balaji Sridharan
 *
 */
@Entity
@Table(name = "unit_area_value")
public class UnitAreaValueBreakup implements Comparable<UnitAreaValueBreakup> {

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
	 * {@link OneToMany} mapping for {@link PropertyTaxPayment}
	 */
	@OneToMany(fetch = FetchType.LAZY)
	private Set<PropertyTaxPayment> propertyTaxPayment;

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

	/**
	 * The setter method
	 * 
	 * @return the propertyTaxPayment
	 */
	public Set<PropertyTaxPayment> getPropertyTaxPayment() {
		return propertyTaxPayment;
	}

	/**
	 * The getter method
	 * 
	 * @param propertyTaxPayment the propertyTaxPayment to set
	 */
	public void setPropertyTaxPayment(Set<PropertyTaxPayment> propertyTaxPayment) {
		this.propertyTaxPayment = propertyTaxPayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		result = prime * result + ((unitAreaValue == null) ? 0 : unitAreaValue.hashCode());
		result = prime * result + ((zone == null) ? 0 : zone.hashCode());
		result = prime * result + ((zoneId == null) ? 0 : zoneId.hashCode());
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
		UnitAreaValueBreakup other = (UnitAreaValueBreakup) obj;
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (categoryId == null) {
			if (other.categoryId != null) {
				return false;
			}
		} else if (!categoryId.equals(other.categoryId)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (statusId == null) {
			if (other.statusId != null) {
				return false;
			}
		} else if (!statusId.equals(other.statusId)) {
			return false;
		}
		if (unitAreaValue == null) {
			if (other.unitAreaValue != null) {
				return false;
			}
		} else if (!unitAreaValue.equals(other.unitAreaValue)) {
			return false;
		}
		if (zone == null) {
			if (other.zone != null)
				return false;
		} else if (!zone.equals(other.zone)) {
			return false;
		}
		if (zoneId == null) {
			if (other.zoneId != null)
				return false;
		} else if (!zoneId.equals(other.zoneId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UnitAreaValueBreakup [id=" + id + ", categoryId=" + categoryId + ", statusId=" + statusId + ", zoneId="
				+ zoneId + ", unitAreaValue=" + unitAreaValue + ", category=" + category + ", status=" + status
				+ ", zone=" + zone + "]";
	}

	@Override
	public int compareTo(UnitAreaValueBreakup o) {
		return this.getId().compareTo(o.getId());
	}

}
