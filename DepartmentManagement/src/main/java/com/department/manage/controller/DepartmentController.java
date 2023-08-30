package com.department.manage.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.department.manage.Entity.HospitalDepartments;
import com.department.manage.departmentservice.DepartmentService;


@Controller
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	

	@GetMapping(path = "addnewdepartment")

	public String user1(Model m) {
		HospitalDepartments hospitalDepartment = new HospitalDepartments();
		
		
		m.addAttribute("department", hospitalDepartment);
		return "newDepartment";
	}

	@PostMapping("/addnewdepartment")

	public String adding_a_Department(@ModelAttribute("department") HospitalDepartments department,Model m)
	{
		boolean service=departmentService.newDepartment(department);
		if(service)
		{
		   m.addAttribute("succes", "addded succesfully");
		  return "newDepartment";
		}
		else
		{
			m.addAttribute("error", "Department not saved ");
			return "newDepartment";
		}
		
	}

	@GetMapping(path = "departments")
	public String fetch_All_Departments(Model m) {

		List<HospitalDepartments> list_Of_Departments = departmentService.getAlldepartment();
		m.addAttribute("departments", list_Of_Departments);
		return "department";

	}

}