package com.botscrew.facebook.handler.senders;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.entity.enums.Postbacks;
import com.botscrew.facebook.messages.MessageKey;
import com.botscrew.facebook.messages.MessagesHolder;
import com.botscrew.facebook.model.outgoing.generic.DefaultAction;
import com.botscrew.facebook.model.outgoing.generic.ListTemplateMessageElement;
import com.botscrew.facebook.model.outgoing.quickreply.QuickReply;
import com.botscrew.facebook.service.MessagesSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

@Component
public class TermsMessageSender {

    @Value("${system.base.url}")
    private String baseUrl;
    @Value("${system.base.privacy}")
    private String policyUrl;
    @Autowired
    private MessagesSender messagesSender;
    @Autowired
    private MessagesHolder messagesHolder;

    public void sendTermsAndConditions(User user) {

        ListTemplateMessageElement listTemplateMessageElement = new ListTemplateMessageElement(
                messagesHolder.getMessage(MessageKey.TERMS_AND_CONDITIONS), null,
                "http://legacymusicgroup.com/wp-content/uploads/2017/03/terms-and-conditions-recropped.jpg");

        DefaultAction defaultAction = new DefaultAction();
        listTemplateMessageElement.setDefaultAction(defaultAction);
        defaultAction.setType("web_url");
        defaultAction.setUrl(policyUrl);
        defaultAction.setMessengerExtensions(true);
        defaultAction.setWebviewHeightRatio("tall");

        messagesSender.sendListTemplate(user, Collections.singletonList(listTemplateMessageElement));

        QuickReply quickReplyDecline = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.DECLINE), Postbacks.TERMS_DECLINE);
        QuickReply quickReplyAccept = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.ACCEPT), Postbacks.TERMS_ACCEPT);

        messagesSender.sendQuickRepliesMessage(user, Arrays.asList(quickReplyAccept, quickReplyDecline),
                messagesHolder.getMessage(MessageKey.ACCEPT_TERMS));
    }

    public void sendAcceptTerms(User user) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        String textWithDay = messagesHolder.getMessage(MessageKey.GREETING_DAY) + " " + getDayOfWeek(dayOfWeek) + "! ";

        String textWithName = messagesHolder.getMessage(MessageKey.WHATS_UP) + ", " + user.getFirstName() + "?";

        messagesSender.sendSimpleMessage(user, textWithDay + textWithName);
    }

    private String getDayOfWeek(int day) {
        switch (day) {
            case Calendar.SUNDAY: {
                return "Sunday";
            }
            case Calendar.MONDAY: {
                return "Monday";
            }
            case Calendar.TUESDAY: {
                return "Tuesday";
            }
            case Calendar.WEDNESDAY: {
                return "Wednesday";
            }
            case Calendar.THURSDAY: {
                return "Thursday";
            }
            case Calendar.FRIDAY: {
                return "Friday";
            }
            case Calendar.SATURDAY: {
                return "Saturday";
            }
            default:
                return "Bad day";
        }
    }
}
