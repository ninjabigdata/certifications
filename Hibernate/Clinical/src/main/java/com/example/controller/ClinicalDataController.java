package com.example.controller;

import com.example.dto.ClinicalDataDTO;
import com.example.entity.ClinicalData;
import com.example.entity.Patient;
import com.example.repository.ClinicalDataRepo;
import com.example.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clinical-data")
@CrossOrigin
public class ClinicalDataController {

    @Autowired
    private ClinicalDataRepo clinicalDataRepo;
    @Autowired
    private PatientRepo patientRepo;

    @PostMapping
    public ClinicalData save(@RequestBody ClinicalDataDTO clinicalDataDTO) {
        ClinicalData clinicalData = new ClinicalData();

        Patient patient = patientRepo.findById(clinicalDataDTO.getPatientId()).orElseThrow(RuntimeException::new);

        clinicalData.setPatient(patient);
        clinicalData.setComponentName(clinicalDataDTO.getComponentName());
        clinicalData.setComponentValue(clinicalDataDTO.getComponentValue());

        clinicalDataRepo.save(clinicalData);

        return clinicalData;
    }

}
