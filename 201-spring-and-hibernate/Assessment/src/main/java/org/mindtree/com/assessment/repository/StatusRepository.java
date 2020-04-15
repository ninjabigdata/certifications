package org.mindtree.com.assessment.repository;

import org.mindtree.com.assessment.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link Repository} iterface for
 * {@link org.mindtree.com.assessment.entity.Status}
 * 
 * @author Balaji Sridharan
 *
 */
public interface StatusRepository extends JpaRepository<Status, Integer> {

}
