package com.cqrs.pattern.cqrs.commands;

import java.util.HashSet;
import java.util.Set;

import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;

public class UpdateUserCommand {

	private String userId;
    private Set<Address> addresses = new HashSet<>();
    private Set<Contact> contacts = new HashSet<>();
    
	public UpdateUserCommand(String userId, Set<Address> addresses, Set<Contact> contacts) {
		super();
		this.userId = userId;
		this.addresses = addresses;
		this.contacts = contacts;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
    
    
    
}
