package com.botscrew.facebook.messages;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MessagesHolder {
	@Autowired
	private Environment environment;

	public String getMessage(MessageKey key) {
		return environment.getProperty(key.value());
	}

	public String getTemplateMessage(MessageKey key, Map<String, String> parameters) {
		String templateMessage = environment.getProperty(key.value());
		for (Entry<String, String> entry : parameters.entrySet()) {
			templateMessage = templateMessage.replace(entry.getKey(), entry.getValue());
		}
		return templateMessage;
	}

}
