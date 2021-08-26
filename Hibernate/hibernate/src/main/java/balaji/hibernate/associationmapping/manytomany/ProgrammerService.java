package balaji.hibernate.associationmapping.manytomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProgrammerService {

    @Autowired
    private ProgrammerRepo repo;

    public Programmer save(Programmer programmer) {
        return repo.save(programmer);
    }

    public Programmer get(Integer id) {
        return repo.eagerFetch(id).orElseGet(null);
    }

    @PostConstruct
    public void test() {
        System.out.println("From ProjectService ManyToMany Example");

        Programmer programmer = new Programmer();
        programmer.setName("John");
        programmer.setSalary(140000);
        Set<Project> projects = new HashSet<>();
        Project project = new Project();
        project.setName("Assignment-1");
        projects.add(project);
        programmer.setProjects(projects);

        System.out.println("Saved programmer is " + save(programmer));

        System.out.println("Retrieved programmer details is " + get(programmer.getId()));
    }

}
