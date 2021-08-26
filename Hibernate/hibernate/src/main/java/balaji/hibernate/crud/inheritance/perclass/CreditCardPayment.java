package balaji.hibernate.crud.inheritance.perclass;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "credit_card_payment")
public class CreditCardPayment extends PaymentPerClass {

    @Column(name = "card_number")
    private String cardNumber;

}
