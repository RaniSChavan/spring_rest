package com.phrc.service;

import java.util.List;

import com.phrc.model.Employee;


public interface EmpService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int id);

	Employee updateEmp(Employee emp, int id);

	void deleteEmp(int id); 

}
