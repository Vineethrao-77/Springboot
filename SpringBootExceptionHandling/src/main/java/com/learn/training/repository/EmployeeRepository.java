package com.learn.training.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.training.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	Employee findBydesignation(String designation);
	Employee findByage(String age);
	Employee findByemailId(String emailId);
	Employee findByfirstName(String firstName);
	Employee findBylastName(String lastName);

}
