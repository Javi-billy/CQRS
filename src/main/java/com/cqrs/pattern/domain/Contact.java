package com.cqrs.pattern.domain;

public class Contact {

	private String type;
	private String detail;

	public Contact(String type, String detail) {
		super();
		this.type = type;
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
