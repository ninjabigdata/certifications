package balaji.hibernate.associationmapping.manytomany;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Programmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer salary;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "programmers_project", joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;

}
