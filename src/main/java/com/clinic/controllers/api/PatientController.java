package com.clinic.controllers.api;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import com.clinic.data.models.Patient;
import com.clinic.data.services.PatientService;
import com.clinic.models.CreatePatient;
import com.clinic.models.UpdatePatient;
import com.clinic.utils.CustomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

	@Autowired
	PatientService patientService;
	
	@PostMapping(value = "/api/patient/", consumes = "application/json")
	public ResponseEntity<?> CreatePatient(@Valid @RequestBody CreatePatient patientRequest, Errors errors){

		Map<String, String> errorsMap = CustomValidationUtils.checkErrors(patientRequest, errors);
		
		if (errorsMap == null){

			Map<String, String> responseMap = new HashMap<>();

			Patient patient = new Patient();
			patient.setName(patientRequest.getName());
			patient.setAdress(patientRequest.getAdress());
			patient.setAge(patientRequest.getAge());
			patient.setCity(patientRequest.getCity());
			patient.setGovernorate(patientRequest.getGovernorate());
			patient.setPhoneNumber(patientRequest.getPhoneNumber());
			patient.setRevisitation(patientRequest.getRevisitation());
			
			
			if (patientService.save(patient).getId() != null) {
				responseMap.put("status","success");			
				return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
			}else{
				responseMap.put("status", "error");
				return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else{
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("status", "error");
			responseMap.put("message", errorsMap);
			
			return  new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
			
		}				
	}



	
	@PutMapping(value="/api/patient/{id}", consumes = "application/json")
	public ResponseEntity<?> updatePatient(@Valid @RequestBody UpdatePatient updatePatient, Errors errors, @PathVariable(value = "id", required = true) Long id){

    
		Map<String, String> errorsMap =  CustomValidationUtils.checkErrors(updatePatient, errors);
		
		if (errorsMap == null) {

			Map<String, String> responseMap = new HashMap<>();
			Patient patient = patientService.findById(id);

			patient.setName(updatePatient.getName());
			patient.setAdress(updatePatient.getAdress());
			patient.setAge(updatePatient.getAge());
			patient.setCity(updatePatient.getCity());
			patient.setGovernorate(updatePatient.getGovernorate());
			patient.setPhoneNumber(updatePatient.getPhoneNumber());
			patient.setRevisitation(updatePatient.getRevisitation());
			patient.setAttended(updatePatient.getAttended());
			patient.setAttendingDate(updatePatient.getAttendingDate());

			if (patientService.save(patient).getId() != null) {
				responseMap.put("status","success");			
				return new ResponseEntity<>(responseMap, HttpStatus.OK);
			}else{
				responseMap.put("status", "error");
				return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else{
			
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("status", "error");
			responseMap.put("message", errorsMap);			
			return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
			
		}	

	}

	@DeleteMapping(value="/api/patient/{id}", consumes = "application/json")
	public ResponseEntity<?> deletePatient(@PathVariable(value = "id", required = true) Long id) {

		Map<String, String> responseMap = new HashMap<>();
		Patient patient = patientService.findById(id);

		patientService.delete(patient);
		responseMap.put("status","success");			
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
		
		
	}
}
