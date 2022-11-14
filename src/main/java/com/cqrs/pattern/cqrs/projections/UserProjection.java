package com.cqrs.pattern.cqrs.projections;

import java.util.Set;

import com.cqrs.pattern.cqrs.queries.AddressByRegionQuery;
import com.cqrs.pattern.cqrs.queries.ContactByTypeQuery;
import com.cqrs.pattern.cqrs.repository.UserReadRepository;
import com.cqrs.pattern.domain.Address;
import com.cqrs.pattern.domain.Contact;
import com.cqrs.pattern.domain.UserAddress;
import com.cqrs.pattern.domain.UserContact;

public class UserProjection {

	private UserReadRepository repository;

	public UserProjection(UserReadRepository repository) {
		this.repository = repository;
	}

	public Set<Contact> handle(ContactByTypeQuery query) throws Exception {
		UserContact userContact = repository.getUserContact(query.getUserId());
		if (userContact == null)
			throw new Exception("User does not exist.");
		return userContact.getContactByType().get(query.getContactType());
	}

	public Set<Address> handle(AddressByRegionQuery query) throws Exception {
		UserAddress userAddress = repository.getUserAddress(query.getUserId());
		if (userAddress == null)
			throw new Exception("User does not exist.");
		return userAddress.getAddressByRegion().get(query.getState());
	}

}
