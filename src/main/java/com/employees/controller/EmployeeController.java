package com.employees.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employees.entity.Employee;
import com.employees.exception.ResourceNotFound;
import com.employees.repository.EmployeeRepository;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeController {
	@Autowired
	private EmployeeRepository repository;
	
	
	//api to get all employee
	@GetMapping("employees")
	public List<Employee> getAllEmployee(){
		return repository.findAll();
		
	}
	
	//api to add all employee
	@PostMapping("employees")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return repository.save(employee);
	}
	//getting employee by his id
	@GetMapping("employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id ){
		Employee employee = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("no record found with this id:" +id));
		return ResponseEntity.ok(employee);
	}
	//api to update the employee
	@PutMapping("employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
		Employee employee2 = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("no record found with this id:" +id));
		employee2.setName(employee.getName());
		employee2.setAddress(employee.getAddress());
		employee2.setSalary(employee.getSalary());
		Employee updateEmploye = repository.save(employee2);
		return ResponseEntity.ok(updateEmploye);		
	}
	
	//api to delete employee
	@DeleteMapping("employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int id){
		Employee employee = repository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("no record found with this id:" +id));
		repository.delete(employee);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	

}
