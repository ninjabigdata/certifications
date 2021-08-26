package com.example.compositeprimarykey;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@IdClass(CustomerId.class)
public class Customer {

//    Lines 16 to 19 for creating composite primary key using IdClass example
//    @Id
//    private Integer id;
//    @Id
//    private String email;

//  Next two lines are for creating composite primary key using @EmbeddedId
    @EmbeddedId
    private CustomerId id;
    @Column(name = "customer_name")
    private String name;
}
