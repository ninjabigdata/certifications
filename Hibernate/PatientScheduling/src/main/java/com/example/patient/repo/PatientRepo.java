package com.example.patient.repo;

import com.example.patient.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<Patient, Integer> {
}
