package com.botscrew.facebook.service.impl;

import com.botscrew.facebook.entity.enums.State;
import com.botscrew.facebook.model.Psid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.botscrew.facebook.dao.UserDAO;
import com.botscrew.facebook.entity.Gender;
import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.model.UserId;
import com.botscrew.facebook.model.incomming.ProfileInfo;
import com.botscrew.facebook.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
	private static final String MALE = "male";
	private static final String FEMALE = "female";

	@Value("${facebook.profile.url}")
	private String profileUrl;
	@Value("${facebook.token}")
	private String TOKEN;
	@Value("${facebook.unlink.url}")
	private String UNLINK_URL;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public User createIfNotExist(UserId chatId) {
		User user = userDAO.findByChatId(chatId.getId());
		if (user == null) {
			user = new User();
			user.setChatId(chatId.getId());
			user.setState(State.INITIAL);
			ProfileInfo profileInfo = getProfileInfo(chatId.getId());
			user.setFirstName(profileInfo.getFirstName());
			user.setLastName(profileInfo.getLastName());
			user.setTimezone(profileInfo.getTimezone());
			if (MALE.equals(profileInfo.getGender())) {
				user.setGender(Gender.MALE);
			} else if (FEMALE.equals(profileInfo.getGender())) {
				user.setGender(Gender.FEMALE);
			}
			userDAO.save(user);
		}
		return user;
	}

	public void unlinkUserAccount(String id) {
		Psid psid = new Psid(id);
		restTemplate.postForObject(UNLINK_URL + TOKEN, psid, String.class);
	}

	public Optional<String> saveLoginAndPasswordAndGetAuthCode(String username, String password, String userID) {
		//process username and password and return authorization code if successful
		return Optional.empty();
	}

	private ProfileInfo getProfileInfo(String userId) {
		String url = profileUrl.replace("$user_id$", userId) + TOKEN;
		return restTemplate.getForObject(url, ProfileInfo.class);
	}

	public void save(User user){
		userDAO.save(user);
	}
}
