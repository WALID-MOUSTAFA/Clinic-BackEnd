package com.clinic.data.services;

import java.util.*;

import com.clinic.data.models.Patient;
import com.clinic.data.repos.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService{

	@Autowired
	PatientRepo patientRepo;
	
	public List<Patient> findAll(){
		return (List<Patient>)patientRepo.findAll();
	}


	public Patient save(Patient patient){
		patientRepo.save(patient);
		return patient;
	}


	public Patient findById(Long id){
		return patientRepo.findById(id).get();
	}

	public void delete(Patient patient){
		patientRepo.delete(patient);
	} 
}

