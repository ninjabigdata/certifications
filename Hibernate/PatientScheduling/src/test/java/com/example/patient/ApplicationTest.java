package com.example.patient;

import com.example.patient.entity.Appointment;
import com.example.patient.entity.Doctor;
import com.example.patient.entity.Insurance;
import com.example.patient.entity.Patient;
import com.example.patient.repo.AppointmentRepo;
import com.example.patient.repo.DoctorRepo;
import com.example.patient.repo.PatientRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Test
    public void testCreateDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Doctor");
        doctor.setLastName("1");
        doctor.setSpeciality("Neuro");

        doctorRepo.save(doctor);
    }

    @Test
    public void testCreatePatient() {
        Patient patient = new Patient();
        patient.setFirstName("Patient");
        patient.setLastName("1");
        patient.setPhone("9874561230");
        Insurance insurance = new Insurance();
        insurance.setCopay(50.0);
        insurance.setProviderName("TTH");
        patient.setInsurance(insurance);
        patient.setDoctors(Arrays.asList(doctorRepo.findById(1).get()));

        patientRepo.save(patient);
    }

    @Test
    public void testAppointment() {
        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(Timestamp.from(Instant.now()));
        appointment.setDoctor(doctorRepo.findById(1).get());
        appointment.setPatient(patientRepo.findById(1).get());
        appointment.setStarted(true);
        appointment.setEnded(false);
        appointment.setReason("Emergency");

        appointmentRepo.save(appointment);
    }

}
