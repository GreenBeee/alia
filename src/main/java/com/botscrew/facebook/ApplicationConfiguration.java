package com.botscrew.facebook;

import com.botscrew.facebook.entity.User;
import com.botscrew.framework.flow.container.LocationContainer;
import com.botscrew.framework.flow.container.PostbackContainer;
import com.botscrew.framework.flow.container.TextContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@PropertySource("classpath:custom-${spring.profiles.active}.properties")
@PropertySource("classpath:messages.properties")
@PropertySource("classpath:general.properties")
public class ApplicationConfiguration {
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("locale/messages");
		return messageSource;
	}
	@Bean
	public LocationContainer<User> locationContainer(){
		return new LocationContainer<>("com.botscrew.facebook.handler", "?");
	}

	@Bean
	public TextContainer<User> textContainer() {
		return new TextContainer<>("com.botscrew.facebook.handler", "?");
	}

	@Bean
	public PostbackContainer<User> postbackContainer() {
		return new PostbackContainer<>("com.botscrew.facebook.handler", "?");
	}
}
