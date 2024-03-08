package com.hakboub.hospitalmanagement;

import com.hakboub.hospitalmanagement.entities.Patient;
import com.hakboub.hospitalmanagement.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner start(PatientRepository patientRepository) {
       return  args -> {
           Patient p1 = new Patient();
           p1.setNom("Akboub");
           p1.setPrenom("Hajar");
           p1.setScore(120);
           p1.setMalade(false);
           p1.setDateNaissance(new Date());

           Patient p2 = new Patient(null,"Saddiki","Mohammed", new Date(), false,1200);

           Patient p3 = Patient.builder()
                   .nom("Salma")
                   .prenom("Imane")
                   .dateNaissance(new Date())
                   .malade(true)
                   .build();
           patientRepository.save(p1);
           patientRepository.save(p2);
           patientRepository.save(p3);


           List<Patient> patients = patientRepository.findAll();

           patients.forEach(p->{
               System.out.println(p.toString());
           });
       };
    }
}

