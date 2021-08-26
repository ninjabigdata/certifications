package com.example.patient.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"patients", "appointments"})
@ToString(exclude = {"patients", "appointments"})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String speciality;
    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients;
    @OneToMany
    private List<Appointment> appointments;

}
