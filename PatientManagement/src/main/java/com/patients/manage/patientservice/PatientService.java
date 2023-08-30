package com.patients.manage.patientservice;



import java.util.List;

import com.patients.manage.entites.Patient;

public interface PatientService
{

public String newPatient(Patient patient);
public List<Patient> getAllPatient();

public List<Patient> searchPatientByName(String fname) ;
public Long getTotalTokens();


}
