package com.cqrs.pattern.es.events;

//@EqualsAndHashCode(callSuper = false)
public class UserContactAddedEvent extends Event {

	private String contactType;
	private String contactDetails;

	public UserContactAddedEvent(String contactType, String contactDetails) {
		super();
		this.contactType = contactType;
		this.contactDetails = contactDetails;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

}
