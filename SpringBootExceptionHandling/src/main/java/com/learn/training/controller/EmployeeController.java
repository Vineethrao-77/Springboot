package com.learn.training.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.training.Exceptions.NameNotFoundException;
import com.learn.training.Exceptions.ResourceNotFoundException;
import com.learn.training.model.Employee;
import com.learn.training.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return service.listAll();
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = service.getByid(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return service.save(employee);
	}

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			 @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = service.getByid(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = service.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = service.getByid(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		service.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	@GetMapping("getemp/{firstName}")
	public Employee getByfirstName(@PathVariable String firstName) throws NameNotFoundException {
		if (service.getByfirstName(firstName) == null)
			throw new NameNotFoundException("Name not found with" + firstName);
		return service.getByfirstName(firstName);

	}

	@GetMapping("getemployee/{lastName}")
	public Employee getByLastName(@PathVariable String lastName) throws NameNotFoundException {
		if (service.getBylastName(lastName) == null)
			throw new NameNotFoundException("Last Name Not found with name " + lastName);
		return service.getBylastName(lastName);
	}
	
	@GetMapping("getEmployeebyemail/{emailId}")
	public Employee getByemaiId(@PathVariable String emailId) {
		if(service.getByemailid(emailId)==null)
			throw new EmployeMailException("Employee mail not Found Exception with " +emailId + "please check and retry");
		return service.getByemailid(emailId);
	}
}
