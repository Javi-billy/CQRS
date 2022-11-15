package com.cqrs.pattern.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cqrs.pattern.cqrs.aggregates.UserAggregate;
import com.cqrs.pattern.cqrs.commands.CreateUserCommand;
import com.cqrs.pattern.cqrs.commands.UpdateUserCommand;
import com.cqrs.pattern.cqrs.projections.UserProjection;
import com.cqrs.pattern.cqrs.projectors.UserProjector;
import com.cqrs.pattern.cqrs.queries.AddressByRegionQuery;
import com.cqrs.pattern.cqrs.queries.ContactByTypeQuery;
import com.cqrs.pattern.cqrs.repository.UserReadRepository;
import com.cqrs.pattern.cqrs.repository.UserWriteRepository;
import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.domain.User;

@RestController
public class CqrsController {	
		
	private UserWriteRepository uwr = new UserWriteRepository();
	private UserReadRepository urr = new UserReadRepository();	
	private UserProjector up = new UserProjector(urr);
		
	@PostMapping(value = "/createUser-cqrs")
	public String createUser(@RequestBody String idUser) throws Exception {
		
		UserAggregate ua = new UserAggregate(uwr);
		User user = ua.handleCreateUserCommand(new CreateUserCommand(idUser,"prueba","otro"));
			
		up.project(user);
		
		return user.getUserid();
	}
	
	@PutMapping(value = "/updateUser-cqrs")
	public String updateUser(@RequestBody String idUser) throws Exception {
		
		UserAggregate ua = new UserAggregate(uwr);
		
		Set<Contact> contacts = new HashSet<>();
		Set<Address> addresses = new HashSet<>();
		
		Contact contact = new Contact("T","647412588");
		contacts.add(contact);
		Address address = new Address("Madrid","Espania","28016"); 
		addresses.add(address);		
		
		User user = ua.handleUpdateUserCommand(new UpdateUserCommand(idUser,addresses,contacts));
		
		up.project(user);
		return user.getUserid();
	}
	
	@GetMapping(value = "/addresses-cqrs/{id}")
	public Set<Address> readAddress(@PathVariable(value = "id") String id) throws Exception {
		UserProjection upr = new UserProjection(urr); 
		return upr.handle(new AddressByRegionQuery(id, "Espania"));
	}
	
	@GetMapping(value = "/contacts-cqrs/{id}")
	public Set<Contact> readContacts(@PathVariable(value = "id") String id) throws Exception {
		UserProjection upr = new UserProjection(urr);
		return upr.handle(new ContactByTypeQuery(id, "T"));
	}	
}
