package balaji.hibernate.idgenerators.controller;

import balaji.hibernate.idgenerators.entity.Employee;
import balaji.hibernate.idgenerators.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("sorted")
    public List<Employee> getSorted() {
        return employeeService.getSorted();
    }

}
