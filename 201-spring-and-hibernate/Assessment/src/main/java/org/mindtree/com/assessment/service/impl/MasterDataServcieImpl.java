/**
 * 
 */
package org.mindtree.com.assessment.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.mindtree.com.assessment.constants.ExceptionCodes;
import org.mindtree.com.assessment.dto.MasterDataDTO;
import org.mindtree.com.assessment.entity.ResidentialPropertyCategory;
import org.mindtree.com.assessment.entity.Status;
import org.mindtree.com.assessment.entity.Zone;
import org.mindtree.com.assessment.exception.ApplicationException;
import org.mindtree.com.assessment.repository.ResidentialPropertyCategoryRepository;
import org.mindtree.com.assessment.repository.StatusRepository;
import org.mindtree.com.assessment.repository.UnitAreaValueRespository;
import org.mindtree.com.assessment.repository.ZoneRepository;
import org.mindtree.com.assessment.repository.projection.CategoryByZone;
import org.mindtree.com.assessment.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The implementation class of {@link MasterDataService}
 * 
 * @author Balaji Sridharan
 *
 */
@Service
public class MasterDataServcieImpl implements MasterDataService {

	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private ZoneRepository zoneRepository;
	@Autowired
	private ResidentialPropertyCategoryRepository categoryRepository;
	@Autowired
	private UnitAreaValueRespository uavRespository;

	@Override
	public List<MasterDataDTO> getStatuses() throws ApplicationException {
		List<Status> statuses = statusRepository.findAll();

		if (statuses.isEmpty()) {
			throw new ApplicationException(ExceptionCodes.MDS_STATUS_NOT_FOUND);
		}

		return statuses.stream().map(status -> {
			MasterDataDTO masterDataDTO = new MasterDataDTO();
			masterDataDTO.setId(status.getId());
			masterDataDTO.setCode(status.getName());

			return masterDataDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<MasterDataDTO> getZones() throws ApplicationException {
		List<Zone> zones = zoneRepository.findAll();

		if (zones.isEmpty()) {
			throw new ApplicationException(ExceptionCodes.MDS_ZONE_NOT_FOUND);
		}

		return zones.stream().map(zone -> {
			MasterDataDTO masterDataDTO = new MasterDataDTO();
			masterDataDTO.setId(zone.getId());
			masterDataDTO.setCode(zone.getName());

			return masterDataDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<MasterDataDTO> getResidentialPropertyCategories() throws ApplicationException {
		List<ResidentialPropertyCategory> categories = categoryRepository.findAll();

		if (categories.isEmpty()) {
			throw new ApplicationException(ExceptionCodes.MDS_CATEGORY_NOT_FOUND);
		}

		return categories.stream().map(category -> {
			MasterDataDTO masterDataDTO = new MasterDataDTO();
			masterDataDTO.setId(category.getId());
			masterDataDTO.setCode(category.getDescription());

			return masterDataDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public List<MasterDataDTO> getResidentialPropertyCategoriesByZone(int zoneId) throws ApplicationException {
		List<CategoryByZone> categories = uavRespository.findDistinctByZoneIdOrderByCategoryId(zoneId);

		if (categories.isEmpty()) {
			throw new ApplicationException(ExceptionCodes.MDS_CATEGORY_NOT_FOUND_FOR_ZONE);
		}

		return categories.stream().map(category -> {
			MasterDataDTO masterDataDTO = new MasterDataDTO();
			masterDataDTO.setId(category.getCategory().getId());
			masterDataDTO.setCode(category.getCategory().getDescription());

			return masterDataDTO;
		}).collect(Collectors.toList());
	}

}
