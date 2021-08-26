package com.example.patient.repo;

import com.example.patient.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepo extends CrudRepository<Appointment, Integer> {
}
