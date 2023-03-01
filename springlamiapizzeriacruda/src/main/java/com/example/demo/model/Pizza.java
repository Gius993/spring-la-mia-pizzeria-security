package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="pizza")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="devi scrivere il nome")
	@NotEmpty(message="deve essere pieno")
	@Column
	private String name;
	
	@NotNull(message="devi descrivere la pizza")
	@NotEmpty(message="deve essere pieno")
	@Size(min = 1, max = 55, message = "devi inserire massimo 55 caratteri")
	@Column
	@Lob
	private String description;
	
	@NotNull(message="inserisci una foto")
	@NotEmpty(message="deve essere pieno")
	@Lob
	private String img;

	@NotNull(message="dai il prezzo alla tua pizza")
	@Max(60)
	@Min(1)
	@Column
	private int price;
	
	
	
	@OneToMany(mappedBy = "pizza")
	
	private List<Promozione> promozione;
	
	@ManyToMany
	
	private List<Ingredienti> ingredienti;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	

	public List<Promozione> getPromozione() {
		return promozione;
	}

	public void setOfferte(List<Promozione> promozione) {
		this.promozione = promozione;
	}
	
	public List<Ingredienti> getIngredienti() {
		return ingredienti;
	}
	public void setIngredienti(List<Ingredienti> ingredienti) {
		this.ingredienti = ingredienti;
	}
}
