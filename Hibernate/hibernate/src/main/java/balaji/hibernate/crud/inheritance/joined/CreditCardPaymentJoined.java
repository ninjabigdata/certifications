package balaji.hibernate.crud.inheritance.joined;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "credit_card_payment_joined")
@PrimaryKeyJoinColumn(name = "id")
public class CreditCardPaymentJoined extends PaymentJoined {

    private Integer id;
    @Column(name = "card_number")
    private String cardNumber;

}
