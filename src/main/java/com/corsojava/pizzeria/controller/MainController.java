package com.corsojava.pizzeria.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping()
	public String index() {
		return "redirect:/pizze";
	}

	@GetMapping("/testTLS")
	public String testTLS(Authentication auth) {
		System.out.println("Utente loggato:" + auth.getName());
		return "testTLS";
	}
}