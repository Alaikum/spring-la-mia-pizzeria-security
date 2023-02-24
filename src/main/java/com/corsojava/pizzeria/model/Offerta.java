package com.corsojava.pizzeria.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Offerta {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Titolo Vuoto")
	@NotNull(message = "Titolo Vuoto")
	@Size(max = 250, message = "Il Titolo deve al massimo 250 caratteri")
	@Size(min = 3, message = "Il Titolo deve avere almeno 3 caratteri")
	private String titolo;
	
	@NotNull(message = "Metti una data")
	@PastOrPresent(message = "Metti una data nel presente o nel passato")
	private LocalDate inizioOfferta;

	
	@NotNull(message = "Metti una data")
	@FutureOrPresent(message = "Metti una data nel presente o nel passato")
	private LocalDate fineOfferta;
	
	@ManyToOne
	@JsonBackReference
	private Pizza pizza;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getInizioOfferta() {
		return inizioOfferta;
	}

	public void setInizioOfferta(LocalDate inizioOfferta) {
		this.inizioOfferta = inizioOfferta;
	}

	public LocalDate getFineOfferta() {
		return fineOfferta;
	}

	public void setFineOfferta(LocalDate fineOfferta) {
		this.fineOfferta = fineOfferta;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	
	

	
}
