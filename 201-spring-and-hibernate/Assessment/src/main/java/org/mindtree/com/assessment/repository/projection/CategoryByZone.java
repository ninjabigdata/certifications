package org.mindtree.com.assessment.repository.projection;

import org.mindtree.com.assessment.entity.ResidentialPropertyCategory;

/**
 * The interface for projection
 * 
 * @author Balaji Sridharan
 *
 */
public interface CategoryByZone {

	/**
	 * For category id
	 * 
	 * @return category id
	 */
	Integer getCategoryId();

	/**
	 * {@link ResidentialPropertyCategory}
	 * 
	 * @return {@link ResidentialPropertyCategory}
	 */
	ResidentialPropertyCategory getCategory();

}
