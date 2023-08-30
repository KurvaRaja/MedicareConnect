package com.department.manage.exception;

public class NoDepartmentFoundException extends RuntimeException
{

	
	private static final long serialVersionUID = 1L; 
	
	public NoDepartmentFoundException(String message)
	{
		super(message);
	}
	
    
}
