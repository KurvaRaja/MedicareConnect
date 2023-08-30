package com.department.manage.departmentservice;

import java.util.List;

import com.department.manage.Entity.HospitalDepartments;

public interface DepartmentService 
{
	public boolean newDepartment( HospitalDepartments department);
	public List< HospitalDepartments> getAlldepartment();	
		
	public Long TotalOfTokens();
}
