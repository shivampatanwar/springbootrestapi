package com.api.springbootrestapi.helper;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseStructure<T> {
	String message;
	T data;
}
