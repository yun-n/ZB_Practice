package com.example.demo.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PracticeController {
	
	@GetMapping("/hello")
	public String helloTest() {
		log.info("controller - hello");
		return "Hello World";
	}
	
	
	@PostMapping("/hi")
	public String hiTest() {
		log.info("controller - hi");
		return "Hi World";
	}

}
