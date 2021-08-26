package com.example.patient.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "appointment_time")
    private Timestamp appointmentTime;
    private Boolean started;
    private Boolean ended;
    private String reason;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

}
