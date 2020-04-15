package org.mindtree.com.assessment.repository;

import java.util.List;

import org.mindtree.com.assessment.entity.ResidentialPropertyCategory;
import org.mindtree.com.assessment.entity.Status;
import org.mindtree.com.assessment.entity.UnitAreaValueBreakup;
import org.mindtree.com.assessment.entity.Zone;
import org.mindtree.com.assessment.repository.projection.CategoryByZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link Repository} for {@link UnitAreaValueBreakup}
 * 
 * @author Balaji Sridharan
 *
 */
public interface UnitAreaValueRespository extends JpaRepository<UnitAreaValueBreakup, Integer> {

	/**
	 * Get the {@link ResidentialPropertyCategory} based on the zoneId
	 * 
	 * @param zoneId based on which {@link ResidentialPropertyCategory} has to be
	 *               fetched
	 * @return the {@link List} of {@link CategoryByZone}
	 */
	List<CategoryByZone> findDistinctByZoneIdOrderByCategoryId(Integer zoneId);

	/**
	 * Get the {@link UnitAreaValueBreakup} based on the given category, zone and
	 * status
	 * 
	 * @param categoryId the id of the {@link ResidentialPropertyCategory}
	 * @param zoneId     the id of the {@link Zone}
	 * @param statusId   the id of the {@link Status}
	 * @return the matching {@link UnitAreaValueBreakup}
	 */
	UnitAreaValueBreakup findByCategoryIdAndZoneIdAndStatusId(Integer categoryId, Integer zoneId, Integer statusId);

}
