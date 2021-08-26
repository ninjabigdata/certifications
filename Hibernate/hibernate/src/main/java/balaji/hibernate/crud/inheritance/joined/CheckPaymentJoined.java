package balaji.hibernate.crud.inheritance.joined;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "check_payment_joined")
@PrimaryKeyJoinColumn(name = "id")
public class CheckPaymentJoined extends PaymentJoined {

    private Integer id;
    @Column(name = "check_number")
    private String checkNumber;

}
