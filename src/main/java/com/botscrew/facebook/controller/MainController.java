package com.botscrew.facebook.controller;

import com.botscrew.facebook.model.incomming.MessageReceived;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.botscrew.facebook.processor.MessagesProcessor;
import com.botscrew.facebook.service.MessengerService;


@RestController
public class MainController {
	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private MessengerService messengerService;
	@Autowired
	private MessagesProcessor messagesProcessor;

	@RequestMapping(value = "/message", method = RequestMethod.POST)
	private void index(@RequestBody(required = false) MessageReceived messageReceived) {
		messagesProcessor.processMessage(messageReceived);
	}

	@RequestMapping(value = "/message", method = RequestMethod.GET)
	private ResponseEntity<String> index(@RequestParam(name = "hub.challenge", required = true) String challenge,
			                             @RequestParam(name = "hub.verify_token", required = true) String verifyToken)
	{
		if (messengerService.checkToken(verifyToken)) {
			return ResponseEntity.ok(challenge);
		}
		LOGGER.warn("Invalid verify token was received : verify_token = " + verifyToken);
		return ResponseEntity.badRequest().body("Invalid verify token");
	}

}
