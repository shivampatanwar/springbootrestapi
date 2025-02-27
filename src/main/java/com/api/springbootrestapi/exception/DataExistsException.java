package com.api.springbootrestapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataExistsException extends RuntimeException {
	String message = "Data Already Exists";
}
