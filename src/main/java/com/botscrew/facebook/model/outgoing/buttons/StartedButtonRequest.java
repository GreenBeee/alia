package com.botscrew.facebook.model.outgoing.buttons;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartedButtonRequest {

    @JsonProperty("get_started")
    private GetStartedButton getStarted;

    public GetStartedButton getGetStarted() {
        return getStarted;
    }

    public void setGetStarted(GetStartedButton getStarted) {
        this.getStarted = getStarted;
    }

    public StartedButtonRequest() {

    }

    public StartedButtonRequest(GetStartedButton getStarted) {

        this.getStarted = getStarted;
    }
}
