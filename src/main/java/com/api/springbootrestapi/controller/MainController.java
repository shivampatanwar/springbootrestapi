package com.api.springbootrestapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String loadSwagger() {
		return "redirect:/swagger-ui/index.html";
	}
}
