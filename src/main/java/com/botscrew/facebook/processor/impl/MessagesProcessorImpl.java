package com.botscrew.facebook.processor.impl;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.exception.SystemException;
import com.botscrew.facebook.model.incomming.*;
import com.botscrew.facebook.model.payment.Payment;
import com.botscrew.facebook.processor.MessagesProcessor;
import com.botscrew.facebook.service.Docfinder;
import com.botscrew.facebook.service.MessagesSender;
import com.botscrew.facebook.service.UserService;
import com.botscrew.framework.flow.container.LocationContainer;
import com.botscrew.framework.flow.container.PostbackContainer;
import com.botscrew.framework.flow.container.TextContainer;
import com.botscrew.framework.flow.model.GeoCoordinates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class MessagesProcessorImpl implements MessagesProcessor {
	private static final Logger LOGGER = LogManager.getLogger(MessagesProcessorImpl.class);

	@Autowired
	private MessagesSender sendMessageService;
	@Autowired
	private UserService userService;
	@Autowired
	private Docfinder docfinder;

	@Autowired
	private PostbackContainer<User> postbackContainer;
	@Autowired
	private TextContainer<User> textContainer;
	@Autowired
	private LocationContainer<User> locationContainer;

	@Override
	public void processMessage(MessageReceived message) {
		try {
			message.getEntry().forEach(entry -> {
				entry.getMessaging().forEach(messaging -> {
					User user = userService.createIfNotExist(messaging.getSender());
					Optional<String> quickReplyOpt = getQuickReply(messaging);
					Optional<String> textMessageOpt = getTextMessage(messaging);
					Optional<String> payloadOpt = getPayload(messaging);
					Optional<Coordinates> coordinatesOpt = getCoordinates(messaging);
					Optional<Referral> referral = getReferral(messaging);
					Optional<Account> account = getAccount(messaging);
					Optional<Payment> paymentOpt = getPayment(messaging);

					if (account.isPresent()) {
						if (account.get().getStatus().equals("linked")) {
							//save authorization code into db
						}
						if (account.get().getStatus().equals("unlinked")) {
							//delete user login and password from db
						}
					}

					if (referral.isPresent()) {
						//do something with referral
					}
					if (coordinatesOpt.isPresent()) {
						locationContainer.processLocation(new GeoCoordinates(coordinatesOpt.get().getLatitude(),
								coordinatesOpt.get().getLongitude()), user);
						return;
					}
					if (quickReplyOpt.isPresent()) {
						postbackContainer.processPostback(quickReplyOpt.get(), user);
						return;
					}
					if (textMessageOpt.isPresent()) {
						// do something with text
						textContainer.processText(textMessageOpt.get(), user);
						return;
					}
					if (payloadOpt.isPresent()) {
						// do something with payload
						postbackContainer.processPostback(payloadOpt.get(), user);
					}
					if (paymentOpt.isPresent()) {
						//do something with payment
					}
				});
			});
		} catch (HttpClientErrorException e) {
			LOGGER.error(e.getResponseBodyAsString(), e);
		} catch (SystemException e) {
			LOGGER.error("System exception", e);
		}
	}

	private Optional<String> getPayload(Messaging messaging) {
		if (messaging.getPostback() != null) {
			return Optional.ofNullable(messaging.getPostback().getPayload());
		}
		return Optional.empty();
	}

	private Optional<String> getQuickReply(Messaging messaging) {
		if (messaging.getMessage() != null
				&& messaging.getMessage().getQuickReply() != null) {
			return Optional.ofNullable(messaging.getMessage().getQuickReply().getPayload());
		}
		return Optional.empty();
	}

	private Optional<String> getTextMessage(Messaging messaging) {
		if (messaging.getMessage() != null) {
			return Optional.ofNullable(messaging.getMessage().getText());
		}
		return Optional.empty();
	}

	private Optional<Coordinates> getCoordinates(Messaging messaging) {
		if (messaging.getMessage() != null) {
			List<Attachment> attachments = messaging.getMessage().getAttachments();
			if (!CollectionUtils.isEmpty(attachments)) {
				Payload payload = attachments.get(0).getPayload();
				if (payload != null) {
					return Optional.ofNullable(payload.getCoordinates());
				}
			}
		}
		return Optional.empty();
	}

	private Optional<Referral> getReferral(Messaging messaging) {
			if (messaging.getReferral() != null) {
				return Optional.ofNullable(messaging.getReferral());
			}
			if (messaging.getPostback() != null && messaging.getPostback().getReferral() != null) {
				return Optional.ofNullable(messaging.getPostback().getReferral());
			}
		return Optional.empty();
	}

	private Optional<Account> getAccount(Messaging messaging) {
		if (messaging.getAccount() != null) {
			return Optional.ofNullable(messaging.getAccount());
		}
		return Optional.empty();
	}

	private Optional<Payment> getPayment(Messaging messaging) {
		if (messaging.getMessage() != null
				&& messaging.getPayment() != null) {
			return Optional.ofNullable(messaging.getPayment());
		}
		return Optional.empty();
	}
}
