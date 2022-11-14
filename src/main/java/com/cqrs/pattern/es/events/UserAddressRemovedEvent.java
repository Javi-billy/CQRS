package com.cqrs.pattern.es.events;

//@EqualsAndHashCode(callSuper = false)
public class UserAddressRemovedEvent extends Event {

	private String city;
	private String state;
	private String postCode;

	public UserAddressRemovedEvent(String city, String state, String postCode) {
		super();
		this.city = city;
		this.state = state;
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

}
