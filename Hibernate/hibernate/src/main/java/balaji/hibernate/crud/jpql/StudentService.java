package balaji.hibernate.crud.jpql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;

    public Student create(Student student) {
        return repo.save(student);
    }

    public List<Student> getAll() {
        return repo.findAllStudents(PageRequest.of(0, 2, Sort.by("lastName"))).orElse(Collections.emptyList());
    }

    public List<Object[]> getAllNames() {
        return repo.findAllNames().orElse(Collections.emptyList());
    }

    public List<Student> getAllByLastName(String lastName) {
        return repo.findAllByLastName(lastName).orElse(Collections.emptyList());
    }

    public List<Student> getAllBetweenScores(int min, int max) {
        return repo.findAllBetweenScores(min, max).orElse(Collections.emptyList());
    }

    public void deleteByFirstName(String firstName) {
        repo.deleteByFirstName(firstName);
    }
}
