package com.corsojava.pizzeria.controller;

import java.util.List;

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

import com.corsojava.pizzeria.repository.IngredienteRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {
	@Autowired
	IngredienteRepository iRepository;
	
	@GetMapping()
	public String index(@RequestParam(name = "nome", required = false) String nome, Model model) {
		List<Ingrediente> ing;
		if (nome != null && !nome.isEmpty()) {
			ing = iRepository.findByNomeLike("%" + nome + "%");
		} else {
			ing=iRepository.findAll(Sort.by("nome"));
		}
		
		model.addAttribute("ingredienti", ing);
		return "ingredienti/index";
	}
	
	
	
	// CREATE CONTROLLER
		@GetMapping("/create") // RICHIESTE GET /BOOKS/CREATE
		public String create(Model model) {
			Ingrediente ingrediente = new Ingrediente(); // non esiste ancora sul DB è solo un nuovo oggetto

			model.addAttribute("ingrediente", ingrediente);
			return "ingredienti/create";
		}
		
		@PostMapping("/create") // RICHIESTE DI TIPO POST cioè quando "invio"
		public String store(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, 
				BindingResult bindingResult,
				Model model) {

			// VALIDAZIONE
			if (bindingResult.hasErrors()) {
				return "ingredienti/create";
			}

			iRepository.save(formIngrediente);

			return "redirect:/ingredienti";
		}
		
		//EDIT
		@GetMapping("/edit/{id}")
		public String edit(@PathVariable("id") Integer id, Model model) {
			Ingrediente ingrediente=iRepository.getReferenceById(id);
			model.addAttribute("ingrediente", ingrediente);
			return "ingredienti/edit";
		}
		//SALVA MODIFICHE
		@PostMapping("/edit/{id}")
		public String update(@Valid @ModelAttribute("ingrediente") Ingrediente formIngrediente, BindingResult bindingResult, Model model) {
			if(bindingResult.hasErrors()) {
				return "ingredienti/edit";
			}
			iRepository.save(formIngrediente);
			return "redirect:/ingredienti";
		}


		// DELETE
		@PostMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id) {
			iRepository.deleteById(id);
			return "redirect:/ingredienti";
		}
}
