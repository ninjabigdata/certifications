package balaji.hibernate.crud.inheritance.singletable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("cc")
public class CreditCard extends Payment {

    @Column(name = "card_number")
    private String cardNumber;

}
