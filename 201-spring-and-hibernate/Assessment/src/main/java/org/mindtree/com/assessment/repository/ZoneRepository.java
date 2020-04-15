package org.mindtree.com.assessment.repository;

import org.mindtree.com.assessment.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link Repository} interface of {@link Zone}
 * 
 * @author Balaji Sridharan
 *
 */
public interface ZoneRepository extends JpaRepository<Zone, Integer> {

}
