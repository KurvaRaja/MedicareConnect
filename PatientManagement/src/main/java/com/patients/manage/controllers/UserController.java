package com.patients.manage.controllers;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.patients.manage.entites.User;
import com.patients.manage.patientservice.PatientService;
import com.patients.manage.patientservice.UserService;

@Controller

public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private PatientService patientsService;

@GetMapping(path = "/home/{username}/{role}")
	
	public String Home(Model m,@PathVariable("username") String username,@PathVariable("role") String role) 
	{
	
	 
		if(role.equals("Doctor"))
		{
			return "DoctorHomepage.html";
		}
		else
		{
			Long tokens = patientsService.getTotalTokens();
			System.out.println(tokens);
			m.addAttribute("totaltokens", tokens);
	 m.addAttribute("username",username);
			return "ReceptionHomepage.html";
		}
	}

	@GetMapping("/register")
	public String showregisterForm(Model m) {
		User user = new User();
		m.addAttribute("user", user);

		return "registration";
	}

	@PostMapping("/register")
	public String saveUser(@ModelAttribute("user") User user, Model m) {

		User emailfrom_databse = userservice.verifyEmail(user.getEmail());
		String string = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12) );
		
		
		
		
		user.setPassword(string);
		
		if (emailfrom_databse == null) 
		{
			userservice.saveUser(user);
			
			
 String username = user.getFullname();
 String role = user.getRole();

			
			return "redirect:/home/" + username +"/"+role;
		} else 
		{
			m.addAttribute("error", "The Email already Exits, Please Login!!");
			
			return "registration";
		}
	}

	@GetMapping("/login")
	public String login(Model m) {
		User user = new User();
		m.addAttribute("user", user);
		return "login";
	}

	@PostMapping("/login")
	public String verifyLoginDetails(@ModelAttribute("user") User user, Model m) {

		User usersfrom_database = userservice.verifyEmail(user.getEmail());

		if (usersfrom_database == null) {
			m.addAttribute("message", "You dont have an account,Please Sign Up");
			return "login";
		}
		boolean isPasswordMatches = org.mindrot.jbcrypt.BCrypt.checkpw(user.getPassword(), usersfrom_database.getPassword());
		if (isPasswordMatches) {

			String fullname = usersfrom_database.getFullname();
			String role = usersfrom_database.getRole();
			return "redirect:/home/" + fullname + "/" + role;
		} else {
			m.addAttribute("message", "Please Enter correct Details");
			return "login";
		}

	}

	@GetMapping("/forgot")
	public String forgotpassword(Model m) {
		User user = new User();
		m.addAttribute("user", user);
		return "forgotpassword";

	}

	@PostMapping("/validateemailandsave")
	
	public String changepassword(@ModelAttribute("user") User user, Model m)
	{
		
		User userEmail = userservice.verifyEmail(user.getEmail());
		
			 if(userEmail!=null && userEmail.getEmail().equals(user.getEmail()))
			 {
				 
			 userEmail.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12) ));
			 
			  userservice.saveUser(userEmail);
			  return "redirect:/login";
			 }
			 else
				 {m.addAttribute("message", "Email does not exist");
				return "forgotpassword";
				 }
		}

}