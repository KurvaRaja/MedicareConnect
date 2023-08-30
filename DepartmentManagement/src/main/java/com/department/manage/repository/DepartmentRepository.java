package com.department.manage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.manage.Entity.HospitalDepartments;

public interface DepartmentRepository  extends JpaRepository<HospitalDepartments, Integer>{

}
