package com.botscrew.facebook.converter;

import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

	@Override
	public java.sql.Timestamp convertToDatabaseColumn(final LocalDateTime entityValue) {
		if (entityValue != null) {
			return java.sql.Timestamp.valueOf(entityValue);
		}
		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(final java.sql.Timestamp databaseValue) {
		if (databaseValue != null) {
			return databaseValue.toLocalDateTime();
		}
		return null;
	}
}
