package com.hakboub.hospitalmanagement.repository;

import com.hakboub.hospitalmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContainingIgnoreCase(String nom);
}
