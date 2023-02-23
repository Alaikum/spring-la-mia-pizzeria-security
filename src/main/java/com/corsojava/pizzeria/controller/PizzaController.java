package com.corsojava.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.pizzeria.model.Ingrediente;
import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.IngredienteRepository;
import com.corsojava.pizzeria.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

	@Autowired
	PizzaRepository pizzaRepository;

	@Autowired
	IngredienteRepository iRepository;
	
	@GetMapping
	public String index(@RequestParam(name = "pizza", required = false) String pizza, Model model) {
		List<Pizza> elencoPizze;
		if (pizza != null && !pizza.isEmpty()) {
			elencoPizze = pizzaRepository.findByNomeLike("%" + pizza + "%");
		} else {
			elencoPizze = pizzaRepository.findAll(Sort.by("nome"));
		}
		model.addAttribute("elencoPizze", elencoPizze);
		return "pizze/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Optional<Pizza> opt = pizzaRepository.findById(id);
		if (opt.isEmpty()) {
			return "redirect:/error";
		}
		model.addAttribute("pizza", opt.get()); // Prendo con id

		return "pizze/show";
	}

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		pizza.setFoto("https://picsum.photos/200"); // valore di default
		List<Ingrediente> listaIngredienti=iRepository.findAll(Sort.by("nome"));  //richiamo ingredienti
		model.addAttribute("listaIngredienti", listaIngredienti);
		model.addAttribute("pizza", pizza);
		
		return "pizze/create";
	}
	
	//CREATE POST MAPPING
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
			BindingResult bindingResult,
			Model model) {
		//validazione
		if(bindingResult.hasErrors()) {
			return "/pizze/create";
		}
		pizzaRepository.save(formPizza);
		return "redirect:/pizze";
	}
	
	//Edit
	//richiesta con post e poi modifica
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Pizza pizza=pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		List<Ingrediente> listaIngredienti=iRepository.findAll(Sort.by("nome"));  //richiamo ingredienti
		model.addAttribute("listaIngredienti", listaIngredienti);
		return "pizze/edit";
	}
	//Salva le modifiche
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "pizze/edit";
		}
		pizzaRepository.save(formPizza);
		
		return "redirect:/pizze";
	}
	
	//Delete
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		pizzaRepository.deleteById(id);
		return "redirect:/pizze";
	}

}
