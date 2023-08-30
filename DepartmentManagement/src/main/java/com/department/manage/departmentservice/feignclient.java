package com.department.manage.departmentservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url="http://localhost:9090",name="PATIENTMS")
public interface feignclient 
{
	@GetMapping("/token")
    public Long gettoken();
}
