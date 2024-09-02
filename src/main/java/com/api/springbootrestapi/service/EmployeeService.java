package com.api.springbootrestapi.service;

import java.util.List;

import com.api.springbootrestapi.dto.Employee;
import com.api.springbootrestapi.exception.DataExistsException;
import com.api.springbootrestapi.exception.DataNotFoundException;
import com.api.springbootrestapi.helper.ResponseStructure;
import com.api.springbootrestapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	ResponseStructure<Employee> structure;

	@Autowired
	ResponseStructure<List<Employee>> structure2;

	public ResponseEntity<ResponseStructure<Employee>> save(Employee employee) {
		if (repository.existsByMobile(employee.getMobile())) {
			throw new DataExistsException("Mobile Number is Repeated : " + employee.getMobile());
		} else {
			repository.save(employee);
			structure.setMessage("Data Saved Success");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> fetchById(String id) {
		Employee employee = repository.findBy_id(id)
				.orElseThrow(() -> new DataNotFoundException("Data Not Found with Id: " + id));
		structure.setMessage("Data Found");
		structure.setData(employee);
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchAll() {
		List<Employee> list = repository.findAll();
		if (list.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			structure2.setMessage("Data Found");
			structure2.setData(list);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure2, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAll(List<Employee> employees) {
		for (Employee employee : employees) {
			if (repository.existsByMobile(employee.getMobile())) {
				throw new DataExistsException("Data Already Exists with Mobile : " + employee.getMobile());
			}
		}
		repository.saveAll(employees);
		structure2.setMessage("Data Saved Success");
		structure2.setData(employees);
		return new ResponseEntity<ResponseStructure<List<Employee>>>(structure2, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchByName(String name) {
		List<Employee> list = repository.findByName(name);
		if (list.isEmpty()) {
			throw new DataNotFoundException("Data Not Found with Name: " + name);
		} else {
			structure2.setMessage("Data Found");
			structure2.setData(list);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure2, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> fetchBySalary(double salary) {
		List<Employee> list = repository.findBySalary(salary);
		if (list.isEmpty()) {
			throw new DataNotFoundException("Data Not FOund WIth Salary: " + salary);
		} else {
			structure2.setMessage("Data Found");
			structure2.setData(list);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure2, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> fetchByMobile(long mobile) {
		Employee employee = repository.findByMobile(mobile);
		if (employee == null) {
			throw new DataNotFoundException("Data Not Found with Mobile :" + mobile);
		} else {
			structure.setMessage("Data Found");
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteById(String _id) {
		Employee employee = repository.findBy_id(_id)
				.orElseThrow(() -> new DataNotFoundException("Data Not Found with Id :" + _id));

		repository.deleteBy_id(_id);
		structure.setMessage("Data Removed");
		structure.setData(employee);
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> update(Employee employee) {
		repository.save(employee);
		structure.setMessage("Data Updated Success");
		structure.setData(employee);
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee, String _id) {
		Employee emp = repository.findBy_id(_id).orElseThrow(() -> new DataNotFoundException());
		if (employee.getName() != null)
			emp.setName(employee.getName());
		if (employee.getMobile() != 0)
			emp.setMobile(employee.getMobile());
		if (employee.getSalary() != 0)
			emp.setSalary(employee.getSalary());
		repository.save(emp);
		structure.setMessage("Data Updated Success");
		structure.setData(employee);
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.OK);
	}

}
