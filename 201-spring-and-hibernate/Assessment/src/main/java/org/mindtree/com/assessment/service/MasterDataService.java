package org.mindtree.com.assessment.service;

import java.util.List;

import org.mindtree.com.assessment.dto.MasterDataDTO;
import org.mindtree.com.assessment.entity.ResidentialPropertyCategory;
import org.mindtree.com.assessment.entity.Status;
import org.mindtree.com.assessment.entity.Zone;
import org.mindtree.com.assessment.exception.ApplicationException;

/**
 * Service interface for fetching master data from DB.<br />
 * <br />
 * 
 * The master data includes zone, UAV, Status and Category details
 * 
 * @author Balaji Sridharan
 *
 */
public interface MasterDataService {

	/**
	 * Get all the {@link Status}
	 * 
	 * @return the {@link List} of {@link Status}
	 * @throws ApplicationException the exception thrown if status master data is
	 *                              not available
	 */
	List<MasterDataDTO> getStatuses() throws ApplicationException;

	/**
	 * Get all the {@link Zone}
	 * 
	 * @return the {@link List} of {@link Zone}
	 * @throws ApplicationException the exception thrown if zone master data is not
	 *                              available
	 */
	List<MasterDataDTO> getZones() throws ApplicationException;

	/**
	 * Get all the {@link ResidentialPropertyCategory}
	 * 
	 * @return the {@link List} of {@link ResidentialPropertyCategory}
	 * @throws ApplicationException the exception thrown if category master data is
	 *                              not available
	 */
	List<MasterDataDTO> getResidentialPropertyCategories() throws ApplicationException;

	/**
	 * Get all the {@link ResidentialPropertyCategory} based on the zone
	 * 
	 * @param zoneId the id of the {@link Zone} based on which
	 *               {@link ResidentialPropertyCategory} has to be fetched
	 * @return the {@link List} {@link ResidentialPropertyCategory} associated to
	 *         the zone
	 * @throws ApplicationException the exception thrown if category master data is
	 *                              not available for the given zone id
	 */
	List<MasterDataDTO> getResidentialPropertyCategoriesByZone(int zoneId) throws ApplicationException;

}
