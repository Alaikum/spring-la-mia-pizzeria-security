package com.corsojava.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.pizzeria.model.Offerta;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {

}
