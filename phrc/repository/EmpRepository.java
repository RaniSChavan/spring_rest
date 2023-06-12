package com.phrc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phrc.model.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{

}
