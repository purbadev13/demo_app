package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Output;
import com.example.demo.service.Service;

@RestController
public class DemoController {

	@Autowired
	Service service;

	@GetMapping("/information")
	public Output info() throws Exception {
		return service.getInfo();
	}
}
