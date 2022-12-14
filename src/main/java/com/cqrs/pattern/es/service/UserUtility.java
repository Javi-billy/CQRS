package com.cqrs.pattern.es.service;

import java.util.List;
import java.util.UUID;

import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.domain.User;
import com.cqrs.pattern.es.events.Event;
import com.cqrs.pattern.es.events.UserAddressAddedEvent;
import com.cqrs.pattern.es.events.UserAddressRemovedEvent;
import com.cqrs.pattern.es.events.UserContactAddedEvent;
import com.cqrs.pattern.es.events.UserContactRemovedEvent;
import com.cqrs.pattern.es.events.UserCreatedEvent;
import com.cqrs.pattern.es.repository.EventStore;

public class UserUtility {

	public static User recreateUserState(EventStore store, String userId) {
		User user = null;

		List<Event> events = store.getEvents(userId);
		for (Event event : events) {
			if (event instanceof UserCreatedEvent) {
				UserCreatedEvent e = (UserCreatedEvent) event;
				user = new User(UUID.randomUUID().toString(), e.getFirstName(), e.getLastName());
			}
			if (event instanceof UserAddressAddedEvent) {
				UserAddressAddedEvent e = (UserAddressAddedEvent) event;
				Address address = new Address(e.getCity(), e.getState(), e.getPostCode());
				if (user != null)
					user.getAddresses().add(address);
			}
			if (event instanceof UserAddressRemovedEvent) {
				UserAddressRemovedEvent e = (UserAddressRemovedEvent) event;
				Address address = new Address(e.getCity(), e.getState(), e.getPostCode());
				if (user != null)
					user.getAddresses().remove(address);
			}
			if (event instanceof UserContactAddedEvent) {
				UserContactAddedEvent e = (UserContactAddedEvent) event;
				Contact contact = new Contact(e.getContactType(), e.getContactDetails());
				if (user != null)
					user.getContacts().add(contact);
			}
			if (event instanceof UserContactRemovedEvent) {
				UserContactRemovedEvent e = (UserContactRemovedEvent) event;
				Contact contact = new Contact(e.getContactType(), e.getContactDetails());
				if (user != null)
					user.getContacts().remove(contact);
			}
		}

		return user;
	}
}
