package com.corsojava.pizzeria.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	
	
	
	@GetMapping
	public String index() {
		return "/index";
	}
	
	@GetMapping("/testTLS")
	public String testTLS(Authentication auth) {
		System.out.println("Utente loggato:" +auth.name());
		return "testTLS";
	}
}
