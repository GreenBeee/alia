package com.botscrew.facebook.handler;


import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.entity.enums.Postbacks;
import com.botscrew.facebook.entity.enums.State;
import com.botscrew.facebook.handler.senders.LocationMessageSender;
import com.botscrew.facebook.handler.senders.TermsMessageSender;
import com.botscrew.facebook.messages.MessageKey;
import com.botscrew.facebook.messages.MessagesHolder;
import com.botscrew.facebook.model.outgoing.quickreply.QuickReply;
import com.botscrew.facebook.service.MessagesSender;
import com.botscrew.facebook.service.UserService;
import com.botscrew.framework.flow.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@PostbackProcessor
@TextProcessor
public class TermsHandler {

    @Autowired
    private MessagesSender messagesSender;
    @Autowired
    private MessagesHolder messagesHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private TermsMessageSender termsMessageSender;
    @Autowired
    private LocationMessageSender locationMessageSender;

    @Postback(states = {State.WAIT_TERMS}, postback = Postbacks.TERMS_ACCEPT)
    public void termsAcceptPostbackProcessor(User user, @PostbackParameters Map<String, String> params) {
        termsMessageSender.sendAcceptTerms(user);

        user.setState(State.ACCEPTED_TERMS);

        userService.save(user);
    }

    @Postback(states = {State.WAIT_TERMS}, postback = Postbacks.TERMS_DECLINE)
    public void termsDeclinePostbackProcessor(User user, @PostbackParameters Map<String, String> params) {
        QuickReply quickReplyStart = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.HI_AGAIN),
                Postbacks.INIT);
        messagesSender.sendQuickRepliesMessage(user, Collections.singletonList(quickReplyStart),
                messagesHolder.getMessage(MessageKey.AWAY));
        user.setState(State.INITIAL);

        userService.save(user);
    }

    @Text(states = {State.WAIT_TERMS})
    public void termsTextProcessor(User user, String text) {
        switch (text) {
            case "Accept":
                termsMessageSender.sendAcceptTerms(user);

                user.setState(State.ACCEPTED_TERMS);

                userService.save(user);
                break;
            case "Decline":
                messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.AWAY));

                user.setState(State.INITIAL);

                userService.save(user);
                break;
            default:
                messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.GREETING_NAME));
                termsMessageSender.sendTermsAndConditions(user);
                break;
        }

    }
    @Postback(states = {State.ACCEPTED_TERMS}, postback = Postbacks.START)
    public void startPostbackProcessor(User user, @PostbackParameters Map<String, String> params) {
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.START));
        locationMessageSender.sendPermissionForLocation(user);
        user.setState(State.WAIT_PERMISSION_LOCATION);

        userService.save(user);
    }

    @Text(states = {State.ACCEPTED_TERMS})
    public void beforeStartTextProcessor(User user, String text) {

        String startString = messagesHolder.getMessage(MessageKey.GREETING_NAME) +
                ", " +
                user.getFirstName() +
                ". ";
        messagesSender.sendSimpleMessage(user, startString);
        QuickReply quickReplyStart = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.GET_STARTED),
                Postbacks.START);
        messagesSender.sendQuickRepliesMessage(user, Collections.singletonList(quickReplyStart),
                messagesHolder.getMessage(MessageKey.BEFORE_START));
    }
}
