package com.botscrew.facebook.model.outgoing.welcome;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WelcomeMessageRequest {
	@JsonProperty("setting_type")
	private String settingsType;
	@JsonProperty("thread_state")
	private String threadState;
	@JsonProperty("call_to_actions")
	private List<Action> callToActions;

	public String getSettingsType() {
		return settingsType;
	}

	public void setSettingsType(String settingsType) {
		this.settingsType = settingsType;
	}

	public String getThreadState() {
		return threadState;
	}

	public void setThreadState(String threadState) {
		this.threadState = threadState;
	}

	public List<Action> getCallToActions() {
		return callToActions;
	}

	public void setCallToActions(List<Action> callToActions) {
		this.callToActions = callToActions;
	}

	public static WelcomeMessageRequest getFullRequest(String payload) {
		WelcomeMessageRequest request = new WelcomeMessageRequest();
		request.setSettingsType("call_to_actions");
		request.setThreadState("new_thread");
		Action action = new Action();
		action.setPayload(payload);
		request.setCallToActions(Arrays.asList(action));
		return request;
	}
}
