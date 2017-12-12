package com.botscrew.facebook.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.botscrew.facebook.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	User findByChatId(String chatId);
}
