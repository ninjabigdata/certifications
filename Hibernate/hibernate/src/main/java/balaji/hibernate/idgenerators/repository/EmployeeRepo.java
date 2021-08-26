package balaji.hibernate.idgenerators.repository;

import balaji.hibernate.idgenerators.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepo extends PagingAndSortingRepository<Employee, Long> {
}
