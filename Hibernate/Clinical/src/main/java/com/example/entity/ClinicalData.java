package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(exclude = "patient")
@ToString(exclude = "patient")
@Entity
@Table(name = "clinicaldata")
@JsonIgnoreProperties("patient")
public class ClinicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "component_name")
    private String componentName;
    @Column(name = "component_value")
    private String componentValue;
    @Column(name = "measured_date_time")
    private Timestamp measuredDateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
    private Patient patient;

}
