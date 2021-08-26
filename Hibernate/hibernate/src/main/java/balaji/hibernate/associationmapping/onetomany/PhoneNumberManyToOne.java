package balaji.hibernate.associationmapping.onetomany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "customer")
@ToString(exclude = "customer")
@Entity
@Table(name = "phone_number_many_to_one")
public class PhoneNumberManyToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String type;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerOneToMany customer;

}
