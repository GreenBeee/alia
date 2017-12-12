package com.botscrew.facebook.model;

import com.botscrew.facebook.model.buttons.UrlButton;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Button {
	private String type;
	private String url;
	private String title;
	private String payload;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		return "Button [type=" + type + ", url=" + url + ", title=" + title + ", payload=" + payload + "]";
	}

	public static Button createPostbackButton(String title, String payload) {
		Button result = new Button();
		result.setTitle(title);
		result.setPayload(payload);
		result.setType("postback");
		return result;
	}

	public static Button createWebUrlButton(String title, String url, boolean messengerExtention, String webViewHeightRatio) {
		UrlButton result = new UrlButton();
		result.setTitle(title);
		result.setUrl(url);
		result.setType("web_url");
		result.setMessengerExtensions(messengerExtention);
		result.setWebviewHeightRatio(webViewHeightRatio);
		return result;
	}

	public static Button createAccountLinkingButton(String callbackUrl) {
		Button button = new Button();
		button.setUrl(callbackUrl);
		button.setType("account_link");
		return button;
	}
}
