package com.example.patient.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "appointments")
@ToString(exclude = "appointments")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Embedded
    private Insurance insurance;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patients_doctors", joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"))
    private List<Doctor> doctors;
    @OneToMany
    private List<Appointment> appointments;

}
