package com.example.patient.repo;

import com.example.patient.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepo extends CrudRepository<Doctor, Integer> {
}
