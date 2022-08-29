package com.wannacode.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.wannacode.microservice.model.Category;

@RestResource(exported = false)
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(String name);
}
