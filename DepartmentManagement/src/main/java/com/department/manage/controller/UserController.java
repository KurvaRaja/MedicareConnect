package com.department.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.department.manage.Entity.User;
import com.department.manage.departmentservice.DepartmentService;
import com.department.manage.userservice.UserService;

@Controller

public class UserController {

	
    @Autowired
	private DepartmentService departmentService;
	
    @Autowired
    private UserService userservice;

	@GetMapping(path = "/home/{username}")

	public String Home(Model m, @PathVariable("username") String username) {

		Long tokens = departmentService.TotalOfTokens();
		m.addAttribute("totaltokens", tokens);
		m.addAttribute("username", username);
		return "Home";
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
		String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(encryptedPassword);

		if (emailfrom_databse == null) {
			userservice.saveUser(user);

			String username = user.getFullname();

			return "redirect:/home/" + username;
		} else {
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
        boolean isPasswordMatches = BCrypt.checkpw(user.getPassword(), usersfrom_database.getPassword());
		System.out.println(isPasswordMatches);
        if (isPasswordMatches ) 
		{

			String fullname = usersfrom_database.getFullname();
			return "redirect:/home/" + fullname;
			
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

	public String changepassword(@ModelAttribute("user") User user, Model m) {

		User userEmail = userservice.verifyEmail(user.getEmail());

		if (userEmail != null && userEmail.getEmail().equals(user.getEmail())) {
			userEmail.setPassword(user.getPassword());
			userservice.saveUser(userEmail);

			return "redirect:/login";
		} else {
			m.addAttribute("message", "Email does not exist");
			return "forgotpassword";
		}
	}

}