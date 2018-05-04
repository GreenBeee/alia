package com.botscrew.facebook.model.buttons;

import com.botscrew.facebook.model.Button;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlButton extends Button {

    @JsonProperty("messenger_extensions")
    private Boolean messengerExtensions;
    @JsonProperty("webview_height_ratio")
    private String webviewHeightRatio;

    public String getWebviewHeightRatio() {
        return webviewHeightRatio;
    }

    public void setWebviewHeightRatio(String webviewHeightRatio) {
        this.webviewHeightRatio = webviewHeightRatio;
    }

    public Boolean getMessengerExtensions() {
        return messengerExtensions;
    }

    public void setMessengerExtensions(Boolean messengerExtensions) {
        this.messengerExtensions = messengerExtensions;
    }
}
