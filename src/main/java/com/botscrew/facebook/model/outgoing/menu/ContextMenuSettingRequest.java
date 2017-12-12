package com.botscrew.facebook.model.outgoing.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ContextMenuSettingRequest {
	@JsonProperty("setting_type")
	private String settingsType;
	@JsonProperty("thread_state")
	private String threadState;
	@JsonProperty("call_to_actions")
	private List<MenuAction> callToActions;

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

	public List<MenuAction> getCallToActions() {
		return callToActions;
	}

	public void setCallToActions(List<MenuAction> callToActions) {
		this.callToActions = callToActions;
	}

	public static ContextMenuSettingRequest getFullRequest(List<MenuAction> menuItems) {
		ContextMenuSettingRequest request = new ContextMenuSettingRequest();
		request.setSettingsType("call_to_actions");
		request.setThreadState("existing_thread");
		request.setCallToActions(menuItems);
		return request;
	}

	@Override
	public String toString() {
		return "ContextMenuSettingRequest{" +
				"settingsType='" + settingsType + '\'' +
				", threadState='" + threadState + '\'' +
				", callToActions=" + callToActions +
				'}';
	}
}
