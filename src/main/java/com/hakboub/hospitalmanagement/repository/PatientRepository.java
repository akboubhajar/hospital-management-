package com.hakboub.hospitalmanagement.repository;

import com.hakboub.hospitalmanagement.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
