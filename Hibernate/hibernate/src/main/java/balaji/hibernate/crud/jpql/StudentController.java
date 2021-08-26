package balaji.hibernate.crud.jpql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("names")
    public List<Object[]> getAllNames() {
        return service.getAllNames();
    }

    @GetMapping("{lastName}")
    public List<Student> getAllByLastNames(@PathVariable("lastName") String lastName) {
        return service.getAllByLastName(lastName);
    }

    @GetMapping("{min}/{max}")
    public List<Student> getAllByLastNames(@PathVariable("min") Integer min, @PathVariable("max") Integer max) {
        return service.getAllBetweenScores(min, max);
    }

    @DeleteMapping("{firstName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByFirstName(@PathVariable("firstName") String firstName) {
        service.deleteByFirstName(firstName);
    }

}
