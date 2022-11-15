package com.cqrs.pattern.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.es.repository.EventStore;
import com.cqrs.pattern.es.service.UserService;

@RestController
public class EsController {	
			
	private EventStore es = new EventStore();
		
	@PostMapping(value = "/createUser-es")
	public String createUser(@RequestBody String idUser) throws Exception {
		
		UserService us = new UserService(es);
		
		us.createUser(idUser, "prueba","otro");
		
		return "OK";
	}
	
	@PutMapping(value = "/updateUser-es")
	public String updateUser(@RequestBody String idUser) throws Exception {
		
		UserService us = new UserService(es);
		
		Set<Contact> contacts = new HashSet<>();
		Set<Address> addresses = new HashSet<>();
		
		Contact contact = new Contact("T","647412588");
		contacts.add(contact);
		Address address = new Address("Madrid","Espania","28016"); 
		addresses.add(address);		
		
		us.updateUser(idUser, contacts, addresses);
		
		return "OK";
	}
	
	@GetMapping(value = "/addresses-es/{id}")
	public Set<Address> readAddress(@PathVariable(value = "id") String id) throws Exception {
		UserService us = new UserService(es); 
		return us.getAddressByRegion(id, "Espania");
	}
	
	@GetMapping(value = "/contacts-es/{id}")
	public Set<Contact> readContacts(@PathVariable(value = "id") String id) throws Exception {
		UserService us = new UserService(es);
		return us.getContactByType(id, "T");
	}	
}
