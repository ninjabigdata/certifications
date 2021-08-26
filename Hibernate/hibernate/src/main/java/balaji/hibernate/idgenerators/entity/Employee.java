package balaji.hibernate.idgenerators.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@Data
public class Employee {
    // Table Generator
//    @TableGenerator(name = "employee_id_gen", table = "id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val", allocationSize = 1000, initialValue = 1000)
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_id_gen")
//    private Long id;

    // Custom Id Generator
    @GenericGenerator(name="emp_id", strategy = "balaji.hibernate.idgenerators.entity.RandomIdGenerator")
    @Id
    @GeneratedValue(generator = "emp_id")
    private Long id;
    private String name;

}
