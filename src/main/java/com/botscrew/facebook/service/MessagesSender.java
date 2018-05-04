package com.botscrew.facebook.service;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.model.Button;
import com.botscrew.facebook.model.outgoing.generic.ListTemplateMessageElement;
import com.botscrew.facebook.model.outgoing.generic.MessageElement;
import com.botscrew.facebook.model.outgoing.quickreply.QuickReply;

import java.util.List;

public interface MessagesSender {
	void sendSimpleMessage(User recipient, String text);

	void sendGenericMessages(User recipient, List<MessageElement> elements);

	void sendButtonsMessage(User recipient, List<Button> buttons, String text);

	void sendQuickRepliesMessage(User recipient, List<QuickReply> quickReplies, String text);

	void sendListTemplate(User recipient, List<ListTemplateMessageElement> elements);
}
