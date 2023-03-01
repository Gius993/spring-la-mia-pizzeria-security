package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Ingredienti;
import com.example.demo.model.Pizza;
import com.example.demo.model.Promozione;
import com.example.demo.repo.Ingredientirepo;
import com.example.demo.repo.Pizzarepo;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	@Autowired
	private Pizzarepo pizzarepo;

	@Autowired
	private Ingredientirepo ingredientirepo;

	@GetMapping
	public String index(Model model) {
		List<Ingredienti> ingredienti = ingredientirepo.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredienti";
	}

	@GetMapping("/create")
	public String create(Model model) {
		Ingredienti ingredienti = new Ingredienti();

		model.addAttribute("ingredienti", ingredienti);

		return "ingredienti-create";
	}

	@PostMapping("/create")
	public String store(
		@ModelAttribute("ingredienti") Ingredienti formIngrediente, Model model) {
		
		ingredientirepo.save(formIngrediente);
		
		return "redirect:/";	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Ingredienti ingredienti = ingredientirepo.getReferenceById(id);
		model.addAttribute("ingredienti", ingredienti);

		return "ingredienti-edit";
	}

	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ingredienti") Ingredienti formIngredienti, BindingResult bindingResult,
			Model model) {

		ingredientirepo.save(formIngredienti);

		return "redirect:/";
	}
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		ingredientirepo.deleteById(id);
		return "redirect:/ingredienti";
	}
}
