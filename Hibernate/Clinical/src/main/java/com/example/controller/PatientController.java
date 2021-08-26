package com.example.controller;

import com.example.entity.ClinicalData;
import com.example.entity.Patient;
import com.example.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/patient")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        this.patientRepo.findAll().forEach(patients::add);

        return patients;
    }

    @GetMapping("{id}")
    public Patient getPatient(@PathVariable(name = "id") Integer id) {
        return this.patientRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return this.patientRepo.save(patient);
    }

    @GetMapping("analyze/{id}")
    public Patient analyze(@PathVariable("id") Integer id) {
        Patient patient = this.patientRepo.findById(id).orElse(null);
        Map<String, ClinicalData> data = new HashMap<>();
        patient.getClinicalData().forEach(clinicalData -> {
            data.putIfAbsent(clinicalData.getComponentName(), clinicalData);
        });
        if (data.containsKey("hw")) {
            String[] heightWeight = data.get("hw").getComponentValue().split("/");
            float height = Float.parseFloat(heightWeight[0]) * 0.4563F;
            float bmi = Float.parseFloat(heightWeight[1]) / (height * height);
            ClinicalData clinicalData = new ClinicalData();
            clinicalData.setPatient(patient);
            clinicalData.setComponentName("bmi");
            clinicalData.setComponentValue(String.valueOf(bmi));
            clinicalData.setMeasuredDateTime(Timestamp.from(Instant.now()));
            data.put(clinicalData.getComponentName(), clinicalData);
            patient.getClinicalData().add(clinicalData);
        }

        patient.getClinicalData().retainAll(data.values());

        return patient;
    }

}
