package com.department.manage.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.department.manage.exception.NoDepartmentFoundException;

@Controller
@RestControllerAdvice
public class GlobalExceptionHandling 
{
      @ExceptionHandler(NoDepartmentFoundException.class)
      public ResponseEntity<APIResponse> handleNoDepartmentFoundException()
      {
    	  APIResponse response=new APIResponse("Not Found","404","No Department Found");
    	  return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }
}
