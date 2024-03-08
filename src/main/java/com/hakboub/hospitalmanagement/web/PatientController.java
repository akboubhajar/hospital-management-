package com.hakboub.hospitalmanagement.web;

import com.hakboub.hospitalmanagement.entities.Patient;
import com.hakboub.hospitalmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("Listpatients",patients);
        return "patients";
    }
    @GetMapping("/deletePatient")
    public String delete(@RequestParam(name = "id") Long id){
        patientRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/editPatient")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            model.addAttribute("p", optionalPatient.get()); // Note the change here: "p" instead of "patient"
            return "editPatient";
        } else {
            return "redirect:/index";
        }
    }

    @PostMapping("/updatePatient")
    public String update(@ModelAttribute("patient") Patient patient) {
        patientRepository.save(patient);
        return "redirect:/index";
    }

    @GetMapping("/addPatient")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/savePatient")
    public String save(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "searchTerm") String searchTerm, Model model) {
        List<Patient> searchResults = patientRepository.findByNomContainingIgnoreCase(searchTerm);
        model.addAttribute("Listpatients", searchResults);
        return "patients";
    }

}
