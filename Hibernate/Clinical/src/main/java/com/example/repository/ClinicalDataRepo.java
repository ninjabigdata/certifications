package com.example.repository;

import com.example.entity.ClinicalData;
import org.springframework.data.repository.CrudRepository;

public interface ClinicalDataRepo extends CrudRepository<ClinicalData, Integer> {
}
