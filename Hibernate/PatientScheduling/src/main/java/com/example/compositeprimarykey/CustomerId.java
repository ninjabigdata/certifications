package com.example.compositeprimarykey;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable // Comment this annotation when using @IdClass fdr creating composite primary key
public class CustomerId implements Serializable {

    private Integer id;
    private String email;

}
