package balaji.hibernate.componentmapping;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "employee_nested")
@Data
public class EmployeeNested {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Embedded
    private Address address;

}
