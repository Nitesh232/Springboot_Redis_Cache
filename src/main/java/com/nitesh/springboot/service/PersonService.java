package com.nitesh.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nitesh.springboot.entity.Persons;

@Service
public class PersonService {

	@Autowired
	private RedisTemplate<String, Persons> template;

	
	
	public String addPerson(Persons pr) {

		System.out.println("Called addPerson() from DB");

		template.opsForHash().put("Persons", pr.getPid(), pr);
		
		

		return "Success";
	}
	
	
	
	public Persons getPerson( Integer id) {

		System.out.println("Called getPerson() from DB");
		
		return (Persons) template.opsForHash().get("Persons", id);
	}



	public List<Object> getAll(){

		System.out.println("Called getAll() from DB");
		
		return template.opsForHash().values("Persons");
	}


	public String deletePerson( Integer id) {
		
		System.out.println("Called deletePerson() from DB");

		template.opsForHash().delete("Persons", id);

		return "Person Successfully deleted";
	}


}
