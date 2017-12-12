package com.botscrew.facebook.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.model.outgoing.DomainWhiteList;
import com.botscrew.facebook.model.outgoing.generic.ListTemplateMessageElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.botscrew.facebook.model.Button;
import com.botscrew.facebook.model.UserId;
import com.botscrew.facebook.model.outgoing.buttons.ButtonTemplateRequest;
import com.botscrew.facebook.model.outgoing.generic.MessageElement;
import com.botscrew.facebook.model.outgoing.generic.MessageRequest;
import com.botscrew.facebook.model.outgoing.quickreply.QuickRepliesRequest;
import com.botscrew.facebook.model.outgoing.quickreply.QuickReply;
import com.botscrew.facebook.model.outgoing.simple.SimpleMessage;
import com.botscrew.facebook.model.outgoing.simple.SimpleMessageRequest;
import com.botscrew.facebook.service.MessagesSender;

import javax.annotation.PostConstruct;

@Primary
@Service
public class MessageSendermpl implements MessagesSender {
	@Value("${facebook.messaging.url}")
	private String MESSAGING_URL;
	@Value("${facebook.token}")
	private String token;
	@Value("${facebook.setwhitelisting.url}")
	private String WHITELISTING_URL;
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void sendSimpleMessage(User recipient, String text) {
		SimpleMessageRequest request = new SimpleMessageRequest();
		request.setRecipient(new UserId(recipient.getChatId()));
		SimpleMessage message = new SimpleMessage();
		message.setText(text);
		request.setMessage(message);
		restTemplate.postForObject(MESSAGING_URL + token, request, String.class);
	}

	@Override
	public void sendGenericMessages(User recipient, List<MessageElement> elements) {
		MessageRequest messageRequest = MessageRequest.getBuilder().elements(elements).recipient(new UserId(recipient.getChatId())).build();
			restTemplate.postForObject(MESSAGING_URL + token, messageRequest, String.class);
	}

	public void sendListTemplate(User recipient, List<ListTemplateMessageElement> elements) {
		List<MessageElement> messageElements = new ArrayList<>(elements);
		MessageRequest messageRequest = MessageRequest.getBuilder().elements(messageElements).recipient(new UserId(recipient.getChatId())).buildListTemplate();
		restTemplate.postForObject(MESSAGING_URL + token, messageRequest, MessageRequest.class);
	}

	@Override
	public void sendButtonsMessage(User recipient, List<Button> buttons, String text) {
		ButtonTemplateRequest request = ButtonTemplateRequest.getBuilder().buttons(buttons).text(text)
				.recipient(new UserId(recipient.getChatId())).build();
		restTemplate.postForObject(MESSAGING_URL + token, request, String.class);
	}

	@Override
	public void sendQuickRepliesMessage(User recipient, List<QuickReply> quickReplies, String text) {
		QuickRepliesRequest request = QuickRepliesRequest.getBuilder().quickReplies(quickReplies).text(text)
				.recipient(new UserId(recipient.getChatId())).build();
		restTemplate.postForObject(MESSAGING_URL + token, request, String.class);
	}

}
