package balaji.hibernate.componentmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeNestedService {

    @Autowired
    private EmployeeNestedRepo repo;

    public List<EmployeeNested> getAllEmployees() {
        List<EmployeeNested> employees = new ArrayList<>();

        repo.findAll().forEach(employees::add);

        return employees;
    }

    public EmployeeNested save(EmployeeNested employee) {
        return  repo.save(employee);
    }

    @PostConstruct
    public void test() {
        System.out.println("From EmployeeNestedService Class");

        System.out.println("List of all existing employees are " + getAllEmployees());

        EmployeeNested employeeNested = new EmployeeNested();
        employeeNested.setName("Ape");
        Address address = new Address();
        address.setStreetAddress("100, Downing Street");
        address.setCity("Chennai");
        address.setState("Tamil Nadu");
        address.setZipCode("600444");
        address.setCountry("India");
        employeeNested.setAddress(address);

        System.out.println("Employee added is " + save(employeeNested));
    }

}
