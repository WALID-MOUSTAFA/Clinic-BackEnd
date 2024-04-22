package com.clinic.controllers;

import java.util.ArrayList;
import java.util.List;

import com.clinic.data.models.Patient;
import com.clinic.data.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@Autowired
	PatientService patientservice;
	
	@GetMapping("/api")
	public List<Patient> Index(){
		return patientservice.findAll();
	}
	
}
