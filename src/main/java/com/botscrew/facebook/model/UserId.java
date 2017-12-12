package com.botscrew.facebook.model;

public class UserId {

	private String id;

	public UserId() {
	}

	public UserId(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserId [id=" + id + "]";
	}

}
