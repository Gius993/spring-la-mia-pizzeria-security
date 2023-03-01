package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Pizza;
import com.example.demo.model.Promozione;
import com.example.demo.repo.Pizzarepo;
import com.example.demo.repo.PromozioneRepo;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/offerte")
public class OffertaController {
	
	@Autowired
	PromozioneRepo promozionerepo;
	
	@Autowired
	Pizzarepo pizzarepo;
	
	@GetMapping
	public String index(Model model) {
		List<Promozione> promozione = promozionerepo.findAll();
		model.addAttribute("promozione", promozione);
		return "promozione";
	}

	@GetMapping("/create")
	public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, Model model) {

		Promozione promozione = new Promozione();
		Pizza pizza = pizzarepo.getReferenceById(pizzaId);
		promozione.setPizza(pizza);
		model.addAttribute("promozione", promozione);
		return "promozione-create";
	}

	
	

	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("promozione") Promozione formPromo, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors())
			return "promozione-create";

		promozionerepo.save(formPromo);

		return "redirect:/details/" + formPromo.getPizza().getId();
	}
}
