package com.botscrew.facebook.model.outgoing.quickreply;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuickReply {

	@JsonProperty(value = "content_type")
	private String contentType;

	private String title;
	private String payload;

	private QuickReply(String contentType) {
		this.contentType = contentType;
	}

	private QuickReply(String contentType, String title, String payload) {
		this(contentType);
		this.title = title;
		this.payload = payload;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "QuickReply [contentType=" + contentType + ", title=" + title + ", payload=" + payload + "]";
	}

	public static QuickReply createTextQuickReply(String title, String payload) {
		return new QuickReply("text", title, payload);
	}

	public static QuickReply createLocationQuickReply() {
		return new QuickReply("location");
	}

}
