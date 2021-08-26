package balaji.hibernate.crud.inheritance.perclass;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "check_payment")
public class CheckPayment extends PaymentPerClass {

    @Column(name = "check_number")
    private String checkNumber;

}
