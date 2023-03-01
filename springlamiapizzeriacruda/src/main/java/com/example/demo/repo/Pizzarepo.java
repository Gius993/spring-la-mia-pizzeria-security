package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pizza;


@Repository
public interface Pizzarepo extends JpaRepository<Pizza, Integer>{
	public List<Pizza> findByNameLike(String keyword);
}
