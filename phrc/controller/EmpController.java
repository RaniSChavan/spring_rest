package com.phrc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phrc.model.Employee;
import com.phrc.service.EmpService;

@RestController
@RequestMapping("/api/rest/")
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("saveEmp")
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"CREATOR\")")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = empService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    @GetMapping("getAllEmployees")
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"CREATOR\",\"EDITOR\")")
    public List<Employee> getAllEmployee(){
    return empService.getAllEmployees(); 
    }
    
    @GetMapping("emp/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int empId){
    	return new ResponseEntity<Employee>(empService.getEmployeeById(empId), HttpStatus.OK);
    }
    
    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyRole(\"ADMIN\",\"EDITOR\")")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
    	return new ResponseEntity<Employee>(empService.updateEmp(employee, id), HttpStatus.OK);
    }
    
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole(\"ADMIN\")")
   public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
	   empService.deleteEmp(id);
	   return new ResponseEntity<String>("Employee deleted successfully", HttpStatus.OK);
   }
}
