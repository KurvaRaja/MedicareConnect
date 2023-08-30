package com.patients.manage.patientrepositoryimplementation;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patients.manage.entites.Patient;
import com.patients.manage.patientrepositories.PatientRepository;
import com.patients.manage.patientservice.PatientService;

@Service
public class PatientServiceImplementation implements PatientService 
{
	private PatientRepository repo;
	
	@Autowired
	private EmailSenderServiceImplementation emailSenderService;
	public PatientServiceImplementation(PatientRepository repo) {
		super();
		this.repo = repo;

	}

	@Override
	public List<Patient> getAllPatient() {
		List<Patient> patients = repo.findAll();


		return patients;
	}

	@Override
	
	public String newPatient(Patient patient) 
	{
		
		Patient Patientsaved = repo.save(patient);
		String successMessage = emailSenderService.sendEmail(Patientsaved.getEmail(),Patientsaved.getFirstname(),Patientsaved.getTokenId());
		return successMessage;
	}

	@Override
	public Long getTotalTokens() {
		long count = repo.count();
		System.out.println(count);
		return count;
	}

	@Override
	public List<Patient> searchPatientByName(String fname) {
		return repo.findByFirstnameOrLastname(fname, fname);
	}

}