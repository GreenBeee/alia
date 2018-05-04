package com.botscrew.facebook.messages.i18n;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.botscrew.facebook.exception.SystemException;

public enum UserLocale {
	EN_US("en", "US"), IT_IT("it", "IT");
	private static final UserLocale DEFAULT = UserLocale.EN_US;
	private String language;
	private String country;

	private UserLocale(String language, String country) {
		this.language = language;
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public String getCountry() {
		return country;
	}

	public static UserLocale getLocale(String value) {
		if (value.indexOf('_') == -1) {
			throw new SystemException("Wrong value of locale " + value);
		}
		String language = value.substring(0, value.indexOf("_"));
		String country = value.substring(value.indexOf("_") + 1);
		List<UserLocale> locales = getLocalesByLanguage(language);
		if (locales.isEmpty()) {
			return DEFAULT;
		}
		Optional<UserLocale> resultOpt = locales.stream()
				.filter(userLocale -> userLocale.getCountry().equalsIgnoreCase(country)).findFirst();
		if (resultOpt.isPresent()) {
			return resultOpt.get();
		}
		// if there is no locale with such country we return the first locale
		// with such language
		// order in enum values defines the priority of locales
		return locales.get(0);
	}

	private static List<UserLocale> getLocalesByLanguage(String language) {
		List<UserLocale> result = new ArrayList<>();
		for (UserLocale userLocale : UserLocale.values()) {
			if (userLocale.getLanguage().equalsIgnoreCase(language)) {
				result.add(userLocale);
			}
		}
		return result;
	}

}
