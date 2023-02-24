package com.corsojava.pizzeria.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.pizzeria.model.Pizza;
import com.corsojava.pizzeria.repository.PizzaRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/pizze")
public class PizzaRestController {
	
	@Autowired
	PizzaRepository pRepository;
	
	
	//INDEX
	@GetMapping()
	public List<Pizza> index(@RequestParam(name = "pizza", required = false) String pizza){
		if (pizza != null && !pizza.isEmpty()) {
			return pRepository.findByNomeLike("%" + pizza + "%");
		} else {
			return pRepository.findAll(Sort.by("nome"));
		}
	}
		//SHOW
	@GetMapping("{id}")
	public ResponseEntity<Pizza> show(@PathVariable("id") Integer id){
		Optional<Pizza> result=pRepository.findById(id);
		if(result.isPresent()) {
			return new ResponseEntity<Pizza>(result.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		}
	}
	
	//CREATE
	@PostMapping("/create")
	public Pizza create(@RequestBody Pizza pizza) {
		return pRepository.save(pizza);
	}
	
	//EDIT
	@PutMapping("{id}") //non serve /edit perchè cambia il metodo
	public Pizza update(@RequestBody Pizza pizza, @PathVariable("id") Integer id) {
		//Pizza p=pRepository.getReferenceById(id);
		//p.setId(id);
		pizza.setId(id);
		return pRepository.save(pizza);
	}
	
	
	@DeleteMapping("{id}")
	public void delete (@PathVariable("id") Integer id)
	{
		pRepository.deleteById(id);
	}
		
		
		
		
		
		//fine controller
}
