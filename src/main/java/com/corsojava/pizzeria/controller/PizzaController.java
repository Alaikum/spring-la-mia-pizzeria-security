package com.corsojava.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

@Controller 
@RequestMapping("/pizze")
public class PizzaController {
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	
	@GetMapping
	public String index(Model model) {
		List<Pizza> elencoPizze=pizzaRepository.findAll();
		model.addAttribute("elencoPizze",elencoPizze);
		
		return"pizze/index";
		
	}

}
