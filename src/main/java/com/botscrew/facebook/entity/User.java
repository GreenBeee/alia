package com.botscrew.facebook.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.botscrew.facebook.converter.LocalDateTimeConverter;
import com.botscrew.framework.flow.model.ChatUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements ChatUser {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "chat_id")
	private String chatId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "timezone")
	private Integer timezone;

	@Column(name = "registered_at")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime registeredAt;

	@Column
	private String state;

	@Override
	public String toString() {
		return "User [id=" + id + ", chatId=" + chatId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", timezone=" + timezone + ", registeredAt=" + registeredAt + "]";
	}

}
