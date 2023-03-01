package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.example.demo.model.Ingredienti;
import com.example.demo.model.Pizza;
import com.example.demo.repo.Ingredientirepo;
import com.example.demo.repo.Pizzarepo;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/")
public class PizzaController {
	@Autowired
	 Pizzarepo pizzarepo;
	@Autowired
	private Ingredientirepo ingredientirepo;
	@GetMapping
	public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Pizza> listaPizze;
		
		
		if(keyword == null) {
			listaPizze = pizzarepo.findAll();
		}else {
			listaPizze = pizzarepo.findByNameLike("%"+keyword+"%"); 
		}
		model.addAttribute("pizze", listaPizze);
		return "pizze";
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable("id") Integer id,Model model) {
		Pizza pizza = pizzarepo.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		return "details";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Pizza pizza = new Pizza();
		List<Ingredienti> ingredienti = ingredientirepo.findAll(Sort.by("name"));
		model.addAttribute("ingredienti", ingredienti);
		model.addAttribute("pizza", pizza);
		return "create";
	}
	@PostMapping("/create")
	public String store(
		@Valid	@ModelAttribute("pizza") Pizza formPizza,
		BindingResult bindingResult,
		Model model) {
		if(bindingResult.hasErrors()) 
			return "create";
		
		pizzarepo.save(formPizza);
		return "redirect:/";
	}	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id,Model model) {
		Pizza pizza = pizzarepo.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		List<Ingredienti> ingredienti = ingredientirepo.findAll(Sort.by("name"));
		model.addAttribute("ingredienti", ingredienti);
		return "edit";
	}
	@PostMapping("/edit/{id}")		
	public String update(
			@Valid @ModelAttribute Pizza formPizza,
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors())
			return "edit";
		
		pizzarepo.save(formPizza);
		
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
	 
	   pizzarepo.deleteById(id);
	   
	   return "redirect:/";
	}
			
}
