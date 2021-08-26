package balaji.hibernate.associationmapping.onetomany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.*;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@ToString(exclude = "phoneNumbers")
@Entity
@Table(name = "customer_one_to_many")
public class CustomerOneToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    // @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<PhoneNumberManyToOne> phoneNumbers;

}
