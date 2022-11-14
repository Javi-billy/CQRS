package com.cqrs.pattern.domain;

import java.util.HashSet;
import java.util.Set;

public class User {

	// @NonNull
	private String userid;
	// @NonNull
	private String firstname;
	// @NonNull

	private String lastname;
	private Set<Contact> contacts = new HashSet<>();
	private Set<Address> addresses = new HashSet<>();

	public User(String userid, String firstname, String lastname, Set<Contact> contacts, Set<Address> addresses) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.contacts = contacts;
		this.addresses = addresses;
	}
	
	public User(String userid, String firstname, String lastname) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;		
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
