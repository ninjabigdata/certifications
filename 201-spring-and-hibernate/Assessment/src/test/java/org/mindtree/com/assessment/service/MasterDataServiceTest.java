package org.mindtree.com.assessment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
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
import org.mindtree.com.assessment.service.impl.MasterDataServcieImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MasterDataServiceTest {

	@InjectMocks
	private MasterDataServcieImpl masterDataServcieImpl;
	@Mock
	private StatusRepository statusRepository;
	@Mock
	private ZoneRepository zoneRepository;
	@Mock
	private ResidentialPropertyCategoryRepository categoryRepository;
	@Mock
	private UnitAreaValueRespository uavRespository;

	@Test
	public void testGetStatues() {
		// Positive Flow
		List<Status> statues = new ArrayList<>();
		Status status = new Status();
		status.setId(1);
		status.setName("Tenanted");
		statues.add(status);
		status = new Status();
		status.setId(2);
		status.setName("Owner");
		statues.add(status);

		doReturn(statues).when(statusRepository).findAll();

		try {
			List<MasterDataDTO> actualResponse = masterDataServcieImpl.getStatuses();

			assertEquals(statues.size(), actualResponse.size());
			assertEquals(statues.get(0).getId(), actualResponse.get(0).getId());
			assertEquals(statues.get(0).getName(), actualResponse.get(0).getCode());
			assertEquals(statues.get(1).getId(), actualResponse.get(1).getId());
			assertEquals(statues.get(1).getName(), actualResponse.get(1).getCode());
		} catch (ApplicationException e) {
			fail();
		}

		// Exception Flow
		doReturn(Collections.EMPTY_LIST).when(statusRepository).findAll();

		try {
			masterDataServcieImpl.getStatuses();
		} catch (ApplicationException exception) {
			assertEquals(ExceptionCodes.MDS_STATUS_NOT_FOUND, exception.getExceptionCode());
		}

	}

	@Test
	public void testGetZones() {
		// Positive Flow
		List<Zone> zones = new ArrayList<>();
		Zone zone = new Zone();
		zone.setId(1);
		zone.setName("Zone A");
		zones.add(zone);
		zone = new Zone();
		zone.setId(2);
		zone.setName("Zone B");
		zones.add(zone);

		doReturn(zones).when(zoneRepository).findAll();

		try {
			List<MasterDataDTO> actualResponse = masterDataServcieImpl.getZones();

			assertEquals(zones.size(), actualResponse.size());
			assertEquals(zones.get(0).getId(), actualResponse.get(0).getId());
			assertEquals(zones.get(0).getName(), actualResponse.get(0).getCode());
			assertEquals(zones.get(1).getId(), actualResponse.get(1).getId());
			assertEquals(zones.get(1).getName(), actualResponse.get(1).getCode());
		} catch (ApplicationException e) {
			fail();
		}

		// Exception Flow
		doReturn(Collections.EMPTY_LIST).when(zoneRepository).findAll();

		try {
			masterDataServcieImpl.getZones();
		} catch (ApplicationException exception) {
			assertEquals(ExceptionCodes.MDS_ZONE_NOT_FOUND, exception.getExceptionCode());
		}

	}

	@Test
	public void testGetResidentialPropertyCategories() {
		// Positive Flow
		List<ResidentialPropertyCategory> categories = new ArrayList<>();
		ResidentialPropertyCategory category = new ResidentialPropertyCategory();
		category.setId(1);
		category.setCategory("I");
		category.setDescription("RCC");
		categories.add(category);
		category = new ResidentialPropertyCategory();
		category.setId(2);
		category.setCategory("I");
		category.setDescription("RCC with lamination");
		categories.add(category);

		doReturn(categories).when(categoryRepository).findAll();

		try {
			List<MasterDataDTO> actualResponse = masterDataServcieImpl.getResidentialPropertyCategories();

			assertEquals(categories.size(), actualResponse.size());
			assertEquals(categories.get(0).getId(), actualResponse.get(0).getId());
			assertEquals(categories.get(0).getDescription(), actualResponse.get(0).getCode());
			assertEquals(categories.get(1).getId(), actualResponse.get(1).getId());
			assertEquals(categories.get(1).getDescription(), actualResponse.get(1).getCode());
		} catch (ApplicationException e) {
			fail();
		}

		// Exception Flow
		doReturn(Collections.EMPTY_LIST).when(categoryRepository).findAll();

		try {
			masterDataServcieImpl.getResidentialPropertyCategories();
		} catch (ApplicationException exception) {
			assertEquals(ExceptionCodes.MDS_CATEGORY_NOT_FOUND, exception.getExceptionCode());
		}

	}

	@Test
	public void testGetResidentialPropertyCategoriesByZone() {
		// Positive Flow
		List<CategoryByZone> categories = new ArrayList<>();
		ResidentialPropertyCategory category = new ResidentialPropertyCategory();
		category.setId(1);
		category.setCategory("I");
		category.setDescription("RCC");
		categories.add(new CategoryByZone() {

			@Override
			public Integer getCategoryId() {
				return 1;
			}

			@Override
			public ResidentialPropertyCategory getCategory() {
				return category;
			}
		});

		int zoneId = 1;
		doReturn(categories).when(uavRespository).findDistinctByZoneIdOrderByCategoryId(zoneId);

		try {
			List<MasterDataDTO> actualResponse = masterDataServcieImpl.getResidentialPropertyCategoriesByZone(zoneId);

			assertEquals(categories.size(), actualResponse.size());
			assertEquals(category.getId(), actualResponse.get(0).getId());
			assertEquals(category.getDescription(), actualResponse.get(0).getCode());
		} catch (ApplicationException e) {
			fail();
		}

		// Exception Flow
		doReturn(Collections.EMPTY_LIST).when(uavRespository).findDistinctByZoneIdOrderByCategoryId(2);

		try {
			masterDataServcieImpl.getResidentialPropertyCategoriesByZone(2);
		} catch (ApplicationException exception) {
			assertEquals(ExceptionCodes.MDS_CATEGORY_NOT_FOUND_FOR_ZONE, exception.getExceptionCode());
		}

	}

}
