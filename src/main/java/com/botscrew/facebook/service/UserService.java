package com.botscrew.facebook.service;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.model.UserId;

import java.util.Optional;

public interface UserService {

	User createIfNotExist(UserId chatId);

	void unlinkUserAccount(String id);

	Optional<String> saveLoginAndPasswordAndGetAuthCode(String username, String password, String userID);

	void save(User user);
}
