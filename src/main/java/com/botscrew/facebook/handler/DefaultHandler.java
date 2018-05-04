package com.botscrew.facebook.handler;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.entity.enums.Postbacks;
import com.botscrew.facebook.entity.enums.State;
import com.botscrew.facebook.handler.senders.TermsMessageSender;
import com.botscrew.facebook.messages.MessageKey;
import com.botscrew.facebook.messages.MessagesHolder;
import com.botscrew.facebook.service.MessagesSender;
import com.botscrew.facebook.service.UserService;
import com.botscrew.framework.flow.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@PostbackProcessor
@TextProcessor
public class DefaultHandler {

    @Autowired
    private MessagesSender messagesSender;
    @Autowired
    private MessagesHolder messagesHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private TermsMessageSender termsMessageSender;

    @Text(states = {State.INITIAL})
    public void initialTextProcessor(User user, String text) {
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.WELCOME));

        termsMessageSender.sendTermsAndConditions(user);

        user.setState(State.WAIT_TERMS);

        userService.save(user);
    }
    @Postback(postback = Postbacks.INIT)
    public void initialPostbackProcessor(User user, @PostbackParameters Map<String, String> params){
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.WELCOME));

        termsMessageSender.sendTermsAndConditions(user);

        user.setState(State.WAIT_TERMS);

        userService.save(user);
    }

}
