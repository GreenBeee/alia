package com.botscrew.facebook.model.incomming;

import java.util.List;

public class MessageReceived {

	private String object;
	private List<Entry> entry;
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public List<Entry> getEntry() {
		return entry;
	}
	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}
	@Override
	public String toString() {
		return "MessageReceived [object=" + object + ", entry=" + entry + "]";
	}
	
}
