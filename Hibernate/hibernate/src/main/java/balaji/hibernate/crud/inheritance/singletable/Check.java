package balaji.hibernate.crud.inheritance.singletable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@DiscriminatorValue("ch")
public class Check extends Payment {

    @Column(name = "check_number")
    private String checkNumber;

}
