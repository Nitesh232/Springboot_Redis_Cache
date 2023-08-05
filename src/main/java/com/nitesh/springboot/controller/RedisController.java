package com.nitesh.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nitesh.springboot.entity.Persons;
import com.nitesh.springboot.service.PersonService;

@RestController
@EnableCaching
public class RedisController {

	
	@Autowired
	private PersonService service;


	@PostMapping("/add")
	@Cacheable("Persons")
	public String addPerson(@RequestBody Persons pr) {
		
		String addPerson = service.addPerson(pr);

		return addPerson;
	}



	@GetMapping("/get/{id}")
	@Cacheable(key = "#id", value = "Persons")
	public Persons getPerson(@PathVariable Integer id) {

		
		return service.getPerson(id);
	}



	@GetMapping("/get")
	@Cacheable("Persons")
	public List<Object> getAll(){

		return service.getAll();
	}


	@DeleteMapping("/delete/{id}")
	public String deletePerson(@PathVariable Integer id) {


		return service.deletePerson(id);
	}


}
