package balaji.hibernate.associationmapping.manytomany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "programmers")
@ToString(exclude = "programmers")
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonBackReference
    @ManyToMany(mappedBy = "projects")
    private Set<Programmer> programmers;

}
