package com.botscrew.facebook.messages.i18n;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.botscrew.facebook.messages.MessageKey;

@Component("i18nHolder")
public class MessagesHolder {
	@Autowired
	private MessageSource messageSource;

	public String getMessage(MessageKey key, UserLocale userLocale) {
		return messageSource.getMessage(key.value(), null,
				new Locale(userLocale.getLanguage(), userLocale.getCountry()));
	}

	public String getTemplateMessage(MessageKey key, UserLocale userLocale, Map<String, String> parameters) {
		String templateMessage = messageSource.getMessage(key.value(), null,
				new Locale(userLocale.getLanguage(), userLocale.getCountry()));
		for (Entry<String, String> entry : parameters.entrySet()) {
			templateMessage = templateMessage.replace(entry.getKey(), entry.getValue());
		}
		return templateMessage;
	}
}
