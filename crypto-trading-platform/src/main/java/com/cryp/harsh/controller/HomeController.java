package com.cryp.harsh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/api/get")
	public String getMessage() {
		return "hey bruh";
	}
}
