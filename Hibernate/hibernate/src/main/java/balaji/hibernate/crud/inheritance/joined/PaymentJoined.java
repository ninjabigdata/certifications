package balaji.hibernate.crud.inheritance.joined;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "payment_joined")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentJoined {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double amount;

}
