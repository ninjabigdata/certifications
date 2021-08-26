package com.example.patient.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class Insurance {

    @Column(name = "provider_name")
    private String providerName;
    private Double copay;

}
