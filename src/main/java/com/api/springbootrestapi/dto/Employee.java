package com.api.springbootrestapi.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.Data;

//@Entity
@Document
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
	
	private String _id;
	private String name;
	private long mobile;
	private double salary;
}
