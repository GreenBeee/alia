package com.botscrew.facebook.model.outgoing.menu;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuAction {

	private String type;
	private String title;
	private String url;
	private String webview_height_ratio;
	private Boolean messenger_extensions;
	private String payload;

	public static MenuAction createPayloadMenuAction(String title, String payload) {
		MenuAction action = new MenuAction();
		action.setType("postback");
		action.setTitle(title);
		action.setPayload(payload);
		return action;
	}

	public static MenuAction createWebUrlMenuAction(String title, String url) {
		MenuAction action = new MenuAction();
		action.setType("web_url");
		action.setTitle(title);
		action.setUrl(url);
		return action;
	}

	public MenuAction setCompactWebView() {
		setMessenger_extensions(Boolean.TRUE);
		setWebview_height_ratio("compact");
		return this;
	}

	public MenuAction setTallWebView() {
		setMessenger_extensions(Boolean.TRUE);
		setWebview_height_ratio("tall");
		return this;
	}

	public MenuAction setFullWebView() {
		setMessenger_extensions(Boolean.TRUE);
		setWebview_height_ratio("full");
		return this;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getWebview_height_ratio() {
		return webview_height_ratio;
	}

	public void setWebview_height_ratio(String webview_height_ratio) {
		this.webview_height_ratio = webview_height_ratio;
	}

	public Boolean getMessenger_extensions() {
		return messenger_extensions;
	}

	public void setMessenger_extensions(Boolean messenger_extensions) {
		this.messenger_extensions = messenger_extensions;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "MenuAction{" +
				"type='" + type + '\'' +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", webview_height_ratio='" + webview_height_ratio + '\'' +
				", messenger_extensions=" + messenger_extensions +
				", payload='" + payload + '\'' +
				'}';
	}
}
