package com.botscrew.facebook.model.outgoing.buttons;

public class Attachment {
	private String type;
	private Payload payload;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Payload getPayload() {
		return payload;
	}
	public void setPayload(Payload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "Attachment [type=" + type + ", payload=" + payload + "]";
	}
	
}
