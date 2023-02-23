package com.corsojava.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.pizzeria.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}
