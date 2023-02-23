package com.corsojava.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.pizzeria.model.Ingrediente;


public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
	public List<Ingrediente> findByNomeLike(String nome);
}
