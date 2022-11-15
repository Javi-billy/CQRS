package com.cqrs.pattern.cqrs.queries;

public class AddressByRegionQuery {

	private String userId;
	private String state;

	public AddressByRegionQuery(String userId, String state) {
		super();
		this.userId = userId;
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
