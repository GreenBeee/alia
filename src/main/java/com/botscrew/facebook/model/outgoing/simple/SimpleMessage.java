package com.botscrew.facebook.model.outgoing.simple;

public class SimpleMessage {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "SimpleMessage [text=" + text + "]";
	}
	
}
