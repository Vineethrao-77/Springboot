package com.learn.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.training.model.Employee;
import com.learn.training.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;

	public Employee save(Employee employee) {
		return repo.save(employee);
	}

	public List<Employee> listAll() {
		return repo.findAll();

	}

	public void delete(Employee employee) {
		repo.delete(employee);
	}

	public Employee getByDesignation(String designation) {
		return repo.findBydesignation(designation);
	}

	public Employee getByemailid(String emailId) {
		return repo.findByemailId(emailId);
	}

	public Employee getByage(String age) {
		return repo.findByage(age);
	}

	public Optional<Employee> getByid(long id) {
		return repo.findById(id);

	}
	public Employee getByfirstName(String firstName) {
		return repo.findByfirstName(firstName);
		
	}
	public Employee getBylastName(String lastName) {
		return repo.findBylastName(lastName);
	}
}