package com.api.springbootrestapi.repository;

import java.util.List;

import com.api.springbootrestapi.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	boolean existsByMobile(long mobile);

	List<Employee> findByName(String name);

	List<Employee> findBySalary(double salary);

	Employee findByMobile(long mobile);

}
