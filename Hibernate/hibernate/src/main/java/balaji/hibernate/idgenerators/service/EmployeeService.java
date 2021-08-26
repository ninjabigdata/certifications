package balaji.hibernate.idgenerators.service;

import balaji.hibernate.idgenerators.entity.Employee;
import balaji.hibernate.idgenerators.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee create(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> getAll() {
        Pageable pageable = PageRequest.of(0, 2);

        Page<Employee> employeePage = employeeRepo.findAll(pageable);

        return employeePage.getContent();
    }

    public List<Employee> getSorted() {
        List<Employee> sortedEmployees = new ArrayList<>();

        // Sort by single column using default sort order
        // employeeRepo.findAll(Sort.by("name")).forEach(sortedEmployees::add);

        // Sort by single column using Direction enum
        // employeeRepo.findAll(Sort.by(Sort.Direction.DESC, "name")).forEach(sortedEmployees::add);

        // Sort multiple columns using same sort order
        // employeeRepo.findAll(Sort.by(Sort.Direction.DESC, "id", "name")).forEach(sortedEmployees::add);

        // Sort multiple columns with sort order for each column
        // employeeRepo.findAll(Sort.by(Sort.Order.desc("id"), Sort.Order.asc("name"))).forEach(sortedEmployees::add);

        // Pageable with sorting
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Order.desc("name")));
        sortedEmployees = employeeRepo.findAll(pageable).getContent();

        return sortedEmployees;
    }

}
