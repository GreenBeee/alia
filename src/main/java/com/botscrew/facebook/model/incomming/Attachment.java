package com.botscrew.facebook.model.incomming;

public class Attachment {
	private String type;
	private Payload payload;
	private String title;
	private String url;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Attachment [type=" + type + ", payload=" + payload + ", title=" + title + ", url=" + url + "]";
	}

	
}
