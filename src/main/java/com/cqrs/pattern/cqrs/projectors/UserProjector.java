package com.cqrs.pattern.cqrs.projectors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.cqrs.pattern.cqrs.repository.UserReadRepository;
import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.domain.User;
import com.cqrs.pattern.domain.UserAddress;
import com.cqrs.pattern.domain.UserContact;

public class UserProjector {

	UserReadRepository readRepository = new UserReadRepository();

	public UserProjector(UserReadRepository readRepository) {
		this.readRepository = readRepository;
	}

	public void project(User user) {
		UserContact userContact = Optional.ofNullable(readRepository.getUserContact(user.getUserid()))
				.orElse(new UserContact());
		Map<String, Set<Contact>> contactByType = new HashMap<>();
		for (Contact contact : user.getContacts()) {
			Set<Contact> contacts = Optional.ofNullable(contactByType.get(contact.getType())).orElse(new HashSet<>());
			contacts.add(contact);
			contactByType.put(contact.getType(), contacts);
		}
		userContact.setContactByType(contactByType);
		readRepository.addUserContact(user.getUserid(), userContact);

		UserAddress userAddress = Optional.ofNullable(readRepository.getUserAddress(user.getUserid()))
				.orElse(new UserAddress());
		Map<String, Set<Address>> addressByRegion = new HashMap<>();
		for (Address address : user.getAddresses()) {
			Set<Address> addresses = Optional.ofNullable(addressByRegion.get(address.getState()))
					.orElse(new HashSet<>());
			addresses.add(address);
			addressByRegion.put(address.getState(), addresses);
		}
		userAddress.setAddressByRegion(addressByRegion);
		readRepository.addUserAddress(user.getUserid(), userAddress);
	}
}
