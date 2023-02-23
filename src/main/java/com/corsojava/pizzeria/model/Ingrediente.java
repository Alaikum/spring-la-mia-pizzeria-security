package com.corsojava.pizzeria.model;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message="L'ingrediente non può essere nullo")
	@NotEmpty (message="L'ingrediente non può essere vuoto")
	private String nome;
	
	
	@ManyToMany(mappedBy="ingredienti")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Pizza> pizze;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Pizza> getPizze() {
		return pizze;
	}


	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	
}
