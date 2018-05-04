package com.botscrew.facebook.messages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImagesHolder {
	private static final String WELCOME_IMAGE = "welcome-image.jpeg";

	@Value("${system.base.url}")
	private String baseUrl;

	private String generateImageUrl(String image) {
		return baseUrl + "/images/" + image;
	}

	public String getWelcomeImageUrl() {
		return generateImageUrl(WELCOME_IMAGE);
	}
}
