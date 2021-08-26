package com.example.repository;

import com.example.entity.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepo extends CrudRepository<Patient, Integer> {
}
