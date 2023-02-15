package com.corsojava.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

	@Autowired
	PizzaRepository pizzaRepository;

	@GetMapping
	public String index(@RequestParam(name = "pizza", required = false) String pizza, Model model) {
		List<Pizza> elencoPizze;
		if (pizza != null && !pizza.isEmpty()) {
			elencoPizze = pizzaRepository.findByNomeLike("%" + pizza + "%");
		} else {
			elencoPizze = pizzaRepository.findAll();
		}
		model.addAttribute("elencoPizze", elencoPizze);
		return "pizze/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Optional <Pizza> opt= pizzaRepository.findById(id);
		if(opt.isEmpty()) {
			return "redirect:/error";
		}
		model.addAttribute("pizza", opt.get()); // Prendo con id

		return "pizze/show";

	}

}
