package balaji.hibernate.componentmapping;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    @Column(name = "street_address")
    private String streetAddress;
    private String city;
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
    private String country;

}
