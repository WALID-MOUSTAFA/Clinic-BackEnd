package com.clinic.data.repos;

import com.clinic.data.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepo extends CrudRepository<Patient, Long> {}
