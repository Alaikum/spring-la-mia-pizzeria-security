package com.corsojava.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Offerta;
import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.OffertaRepository;
import com.corsojava.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/offerte")
public class OffertaController {

	@Autowired
	PizzaRepository pRepository;
	
	@Autowired
	OffertaRepository oRepository;
	
	@GetMapping("/create")
	public String create(@RequestParam(name="pizzaId",required=true) Integer pizzaId, Model model) {
		Offerta offerta= new Offerta();
		Pizza pizza=pRepository.getReferenceById(pizzaId);
		offerta.setPizza(pizza);
		model.addAttribute("offerta", offerta);
		return "offerte/create";
	}
	
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("offerta") Offerta formOfferta,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "offerte/create";
		}
		oRepository.save(formOfferta);
		return "redirect:/pizze/"+formOfferta.getPizza().getId();
	}
	
	//EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Offerta offerta=oRepository.getReferenceById(id);
		model.addAttribute("offerta", offerta);
		return "offerte/edit";
	}
	//SALVA MODIFICHE
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("offerta") Offerta formOfferta, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "offerte/edit";
		}
		oRepository.save(formOfferta);
		return "redirect:/pizze/"+formOfferta.getPizza().getId();
	}
}
