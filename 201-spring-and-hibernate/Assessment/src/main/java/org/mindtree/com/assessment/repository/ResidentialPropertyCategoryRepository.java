package org.mindtree.com.assessment.repository;

import org.mindtree.com.assessment.entity.ResidentialPropertyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link Repository} interface of {@link ResidentialPropertyCategory}
 * 
 * @author Balaji Sridharan
 *
 */
public interface ResidentialPropertyCategoryRepository extends JpaRepository<ResidentialPropertyCategory, Integer> {

}
