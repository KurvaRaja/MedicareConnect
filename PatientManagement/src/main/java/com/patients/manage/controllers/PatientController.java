package com.patients.manage.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.patients.manage.entites.Patient;
import com.patients.manage.patientservice.PatientService;



@Controller
public class PatientController 
{
	@Autowired
	private PatientService patient_service;


	@GetMapping("/newpatient")
	public String createPatientForm(Model m) {
		Patient patient = new Patient();
		m.addAttribute("patient", patient);
		return "addingnewPatient";
	}

	@PostMapping("/newpatient")
	public String newpatient(@ModelAttribute("patient") Patient patient,Model m)
	{
		String savedpatient = patient_service.newPatient(patient);
		m.addAttribute("success",savedpatient );
		
		return "redirect:/newpatient";
		
	}

	/* This for the patient table*/
	@GetMapping("/patients")
	public ModelAndView getALLPatients(Model m,@Param("name") String name)
	{
		ModelAndView mv=new ModelAndView();
		if(name!=null )
		{
			List<Patient> patientByName = patient_service.searchPatientByName(name);
			System.out.println(patientByName.isEmpty());
			if(!patientByName.isEmpty())
			{
			 m.addAttribute("allPatient", patient_service.searchPatientByName(name));
			 mv.setViewName("patient");
			 return mv;
			}
			else
			{
				m.addAttribute("noPatientFound", "No Patient found with the"+" "+name);
				mv.setViewName("patient");
				return mv;
				
			}
		}
		else
		{
		List<Patient> allPatient = patient_service.getAllPatient();
		m.addAttribute("allPatient", allPatient);
		 mv.setViewName("patient");
	 return mv;
		}
	}

	
	
	
	@GetMapping("/tokens")
	@ResponseBody
	public Long gettoken()
	{
		return patient_service.getTotalTokens();
	}

	
	
	


}