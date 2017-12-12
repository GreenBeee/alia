package com.botscrew.facebook.model.outgoing.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/14/2016.
 */
public class DefaultAction {
    private String type;

    private String url;

    @JsonProperty("messenger_extensions")
    private boolean messengerExtensions;

    @JsonProperty("webview_height_ratio")
    private String webviewHeightRatio;

    @JsonProperty("fallback_url")
    private String fallbackUrl;

    public DefaultAction() {
    }

    private DefaultAction(String url, boolean messengerExtensions, String webviewHeightRatio, String fallbackUrl) {
        this.url = url;
        this.messengerExtensions = messengerExtensions;
        this.webviewHeightRatio = webviewHeightRatio;
        this.fallbackUrl = fallbackUrl;
    }

    public static DefaultAction createWebUrlDefaultActionWithExtensions(String url, String fallbackUrl, String webviewHeightRatio) {
        DefaultAction defaultAction = new DefaultAction(url, true, webviewHeightRatio, fallbackUrl);
        defaultAction.setType("web_url");
        return defaultAction;
    }

    public static DefaultAction createWebUrlDefaultActionWithoutExtensions(String url) {
        DefaultAction defaultAction = new DefaultAction();
        defaultAction.setType("web_url");
        defaultAction.setUrl(url);
        return defaultAction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFallbackUrl() {
        return fallbackUrl;
    }

    public void setFallbackUrl(String fallbackUrl) {
        this.fallbackUrl = fallbackUrl;
    }

    public String getWebviewHeightRatio() {
        return webviewHeightRatio;
    }

    public void setWebviewHeightRatio(String webviewHeightRatio) {
        this.webviewHeightRatio = webviewHeightRatio;
    }

    public boolean isMessengerExtensions() {
        return messengerExtensions;
    }

    public void setMessengerExtensions(boolean messengerExtensions) {
        this.messengerExtensions = messengerExtensions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
