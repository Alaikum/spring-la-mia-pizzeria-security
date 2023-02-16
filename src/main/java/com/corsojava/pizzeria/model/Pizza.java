package com.corsojava.pizzeria.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Campo Nome Vuoto")
	@NotNull(message = "Campo Nome Vuoto")
	@Size(max = 250, message = "Il Nome deve al massimo 250 caratteri")
	@Size(min = 3, message = "Il Nome deve avere almeno 3 caratteri")
	private String nome;
	
	@NotEmpty(message = "Campo Descrizione Vuoto")
	@NotNull(message = "Campo Descrizione Vuoto")
	@Size(max = 250, message = "La Descrizione deve al massimo 250 caratteri")
	@Size(min = 3, message = "La Descrizione deve avere almeno 3 caratteri")
	private String descrizione;
	
	@NotEmpty(message = "Campo Foto Vuoto")
	@NotNull(message = "Campo Foto Vuoto")
	@Size(max = 250, message = "La Foto deve al massimo 250 caratteri")
	@Size(min = 3, message = "La Foto deve avere almeno 3 caratteri")
	private String foto;

	@NotNull(message = "Campo Prezzo Vuoto")
	@DecimalMin(value = "0.01", message = "Il Prezzo deve esse Maggiore di 0,01")
	private BigDecimal prezzo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getId() {
		return id;
	}

}
