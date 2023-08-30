package com.department.manage.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.manage.Entity.HospitalDepartments;
import com.department.manage.departmentservice.DepartmentService;
import com.department.manage.departmentservice.feignclient;
import com.department.manage.exception.NoDepartmentFoundException;
import com.department.manage.repository.DepartmentRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class DepartmentImplementation implements DepartmentService 
{
	@Autowired
	private DepartmentRepository departmentrepository;
	@Autowired
	private feignclient feignClient;
	
	@Override
	public boolean newDepartment(HospitalDepartments department) 
	{
		 HospitalDepartments hospitalDepartment = departmentrepository.save(department);
		
		if(hospitalDepartment!=null) return true;

		return false;
	}
	
	@Override
	public List<HospitalDepartments> getAlldepartment() 
	{
		List<HospitalDepartments> all_departments = departmentrepository.findAll();
		if (all_departments.size() != 0) 
		{

			return all_departments;

		} 
		else 
		{
			throw new NoDepartmentFoundException("no department Available currently");
		}

	}

	
	
	
	@Override
	@CircuitBreaker(name="${spring.application.name}",fallbackMethod="getdefaultToken")
	public Long TotalOfTokens() 
	{
		Long tokens = feignClient.gettoken();
		System.out.println(tokens);
		return tokens;
	}
	
	public Long getdefaultToken( Exception e) 
	{
		
		return 0L;
	}


}
	

