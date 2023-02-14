package com.corsojava.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.pizzeria.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

}
