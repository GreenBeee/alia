package com.botscrew.facebook.model.outgoing.buttons;

public class GetStartedButton {

    private String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public GetStartedButton(String payload) {

        this.payload = payload;
    }

    public GetStartedButton() {

    }
}
