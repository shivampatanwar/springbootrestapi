package com.api.springbootrestapi.repository;

import java.util.List;
import java.util.Optional;

import com.api.springbootrestapi.dto.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

//public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
public interface EmployeeRepository extends MongoRepository<Employee, Integer> {

	boolean existsByMobile(long mobile);

	List<Employee> findByName(String name);

	List<Employee> findBySalary(double salary);

	Employee findByMobile(long mobile);

	Optional<Employee> findBy_id(String _id);

	void deleteBy_id(String _id);


}
