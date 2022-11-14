package com.cqrs.pattern.cqrs.queries;

public class ContactByTypeQuery {

	private String userId;
	private String contactType;

	public ContactByTypeQuery(String userId, String contactType) {
		super();
		this.userId = userId;
		this.contactType = contactType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

}
