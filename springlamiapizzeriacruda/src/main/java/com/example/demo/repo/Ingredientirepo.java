package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ingredienti;

@Repository
public interface Ingredientirepo extends JpaRepository<Ingredienti, Integer> {

}
