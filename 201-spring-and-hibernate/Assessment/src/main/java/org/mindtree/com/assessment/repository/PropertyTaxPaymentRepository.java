package org.mindtree.com.assessment.repository;

import java.util.List;

import org.mindtree.com.assessment.entity.PropertyTaxPayment;
import org.mindtree.com.assessment.repository.projection.ZonalWiseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The {@link Repository} interface for {@link PropertyTaxPayment}
 * 
 * @author Balaji Sridharan
 *
 */
public interface PropertyTaxPaymentRepository extends JpaRepository<PropertyTaxPayment, Long> {

	/**
	 * Native query to fetch the zonal wise report for the current year
	 * 
	 * @return the {@link List} of {@link ZonalWiseReport}
	 */
	@Query(value = "SELECT (SELECT name FROM status WHERE id = status_id) AS property_type, (SELECT name FROM zone WHERE id = zone_id) AS zone_name, ROUND(SUM(total_tax_paid), 2) AS amount_collected FROM unit_area_value JOIN (SELECT uav_id, total_tax_paid FROM property_tax_payment WHERE year_of_assessment = YEAR(CURRENT_DATE())) ON unit_area_value.id = uav_id GROUP BY status_id, zone_id ORDER BY zone_name, property_type;", nativeQuery = true)
	List<ZonalWiseReport> getZoneWiseReport();

}
