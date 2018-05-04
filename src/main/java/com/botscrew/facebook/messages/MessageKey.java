package com.botscrew.facebook.messages;

public enum MessageKey {
	//@formatter:off
	WELCOME("message.welcome"),
	ACCEPT_TERMS("message.terms"),
	GREETING_DAY("message.greeting_day"),
	GREETING_NAME("message.greeting_name"),
	AWAY("message.away"),
	HELP_QUESTION("message.help_question"),
	LOCATION_PERMISSION1("message.location_permission1"),
	LOCATION_PERMISSION2("message.location_permission2"),
	START("message.start"),
	BEFORE_START("message.before_start"),
	BAD_UNDERSTANDING("message.bad_understanding"),
	THANKS("message.thanks"),
	GET_STARTED("message.get_started"),
	WORK("message.work"),
	NO_LOCATION("message.no_location"),
	ACCEPT("message.accept"),
	DECLINE("message.decline"),
	WHATS_UP("message.whats_up"),
	HI_AGAIN("message.hi_again"),
	CONTINUE("message.continue"),
	NO("message.no"),
	SEND_LOCATION("message.send_location"),
	FROM_START("message.begin_from_start"),
	DOCTOR_NOT_FOUND("message.doctor_not_found"),
	TERMS_AND_CONDITIONS("message.terms_and_conditions");
	//@formatter:on
	private String value;

	public String value() {
		return value;
	}

	private MessageKey(String value) {
		this.value = value;
	}
}
