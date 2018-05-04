package com.botscrew.facebook.handler;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.entity.enums.Postbacks;
import com.botscrew.facebook.entity.enums.State;
import com.botscrew.facebook.handler.senders.LocationMessageSender;
import com.botscrew.facebook.messages.MessageKey;
import com.botscrew.facebook.messages.MessagesHolder;
import com.botscrew.facebook.model.outgoing.generic.MessageElement;
import com.botscrew.facebook.service.Docfinder;
import com.botscrew.facebook.service.MessagesSender;
import com.botscrew.facebook.service.UserService;
import com.botscrew.framework.flow.annotation.*;
import com.botscrew.framework.flow.model.GeoCoordinates;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@PostbackProcessor
@TextProcessor
@LocationProcessor
public class LocationHandler {
    @Autowired
    private MessagesSender messagesSender;
    @Autowired
    private MessagesHolder messagesHolder;
    @Autowired
    private UserService userService;
    @Autowired
    private Docfinder docfinder;
    @Autowired
    private LocationMessageSender locationMessageSender;

    @Postback(states = {State.WAIT_PERMISSION_LOCATION}, postback = Postbacks.LOCATION_PERMISSION_ACCEPT)
    public void acceptPermissionLocationPostbackProcessor(User user, @PostbackParameters Map<String, String> params) {
        locationMessageSender.sendLocationMessage(user);

        user.setState(State.WAIT_LOCATION);

        userService.save(user);
    }

    @Postback(states = {State.WAIT_PERMISSION_LOCATION}, postback = Postbacks.LOCATION_PERMISSION_DECLINE)
    public void declinePermissionLocationPostbackProcessor(User user, @PostbackParameters Map<String, String> params) {
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.NO_LOCATION));
        locationMessageSender.sendPermissionForLocation(user);
    }

    @Text(states = {State.WAIT_PERMISSION_LOCATION})
    public void textPermissionLocationPostbackProcessor(User user, String text) {
        switch (text) {
            case "Continue": {
                locationMessageSender.sendLocationMessage(user);
                user.setState(State.WAIT_LOCATION);
                userService.save(user);
                break;
            }
            case "No": {
                messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.NO_LOCATION));
                locationMessageSender.sendPermissionForLocation(user);
                break;
            }
            default:
                locationMessageSender.sendBadUnderstandLocation(user);
                break;
        }
    }

    @Text(states = {State.WAIT_LOCATION})
    public void textLocationPostbackProcessor(User user, String text) {
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.BAD_UNDERSTANDING));
        locationMessageSender.sendLocationMessage(user);
    }

    @Location(states = {State.WAIT_LOCATION})
    public void locationProcessor(User user, GeoCoordinates coordinates) {
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.THANKS));
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.WORK));

        List<MessageElement> listMessageElements = locationMessageSender.formDoctorsMessageElements(coordinates);

        int toIndex = 9;
        toIndex = toIndex < listMessageElements.size() ? toIndex : listMessageElements.size();
        if (listMessageElements.size() > 0) {
            messagesSender.sendGenericMessages(user, listMessageElements.subList(0, toIndex));
        } else {
            messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.DOCTOR_NOT_FOUND));
        }

        locationMessageSender.sendEndFlow(user);

        userService.save(user);

        user.setState(State.HAD_LOCATION);
        userService.save(user);
    }

    @Text(states = {State.HAD_LOCATION})
    public void endFlowTextProcessor(User user, String text) {
        locationMessageSender.sendEndFlow(user);
    }
}
