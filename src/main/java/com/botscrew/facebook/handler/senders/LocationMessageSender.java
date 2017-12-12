package com.botscrew.facebook.handler.senders;

import com.botscrew.facebook.entity.User;
import com.botscrew.facebook.entity.enums.Postbacks;
import com.botscrew.facebook.messages.MessageKey;
import com.botscrew.facebook.messages.MessagesHolder;
import com.botscrew.facebook.model.Button;
import com.botscrew.facebook.model.Doctor;
import com.botscrew.facebook.model.outgoing.generic.MessageElement;
import com.botscrew.facebook.model.outgoing.quickreply.QuickReply;
import com.botscrew.facebook.service.Docfinder;
import com.botscrew.facebook.service.MessagesSender;
import com.botscrew.framework.flow.model.GeoCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class LocationMessageSender {

    @Autowired
    private MessagesSender messagesSender;
    @Autowired
    private MessagesHolder messagesHolder;
    @Autowired
    private Docfinder docfinder;
    @Value("${system.base.url}")
    private String baseUrl;



    public void sendPermissionForLocation(User user){
        QuickReply quickReplyAccept = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.CONTINUE),
                Postbacks.LOCATION_PERMISSION_ACCEPT);
        QuickReply quickReplyDecline = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.NO),
                Postbacks.LOCATION_PERMISSION_DECLINE);

        messagesSender.sendQuickRepliesMessage(user, Arrays.asList(quickReplyAccept, quickReplyDecline),
                messagesHolder.getMessage(MessageKey.LOCATION_PERMISSION1));
    }
    public void sendLocationMessage(User user){

        QuickReply quickReplyLocation = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.SEND_LOCATION),
                Postbacks.LOCATION);
        quickReplyLocation.setContentType("location");
        messagesSender.sendQuickRepliesMessage(user, Collections.singletonList(quickReplyLocation),
                messagesHolder.getMessage(MessageKey.LOCATION_PERMISSION2));
    }
    public void sendBadUnderstandLocation(User user){
        messagesSender.sendSimpleMessage(user, messagesHolder.getMessage(MessageKey.BAD_UNDERSTANDING));
        sendPermissionForLocation(user);
    }
    public void sendEndFlow(User user){
        QuickReply quickReplyStart = QuickReply.createTextQuickReply(messagesHolder.getMessage(MessageKey.FROM_START),
                Postbacks.INIT);
        messagesSender.sendQuickRepliesMessage(user, Collections.singletonList(quickReplyStart),
                messagesHolder.getMessage(MessageKey.HELP_QUESTION));
    }

    public List<MessageElement> formDoctorsMessageElements(GeoCoordinates coordinates) {
        List<Doctor> doctorList = docfinder.findAllSortedDistanceDoctors(coordinates);
        List<MessageElement> messageElementList = new ArrayList<>();
        for (Doctor doctor : doctorList){
            MessageElement element = new MessageElement();
            UriComponentsBuilder uriComponents = UriComponentsBuilder.fromUri(URI.create(baseUrl + "/doctor"))
                    .queryParam("id", doctor.getId())
                    .queryParam("long", coordinates.getLongitude())
                    .queryParam("lat", coordinates.getLatitude());
            Button button = Button.createWebUrlButton("Show", uriComponents.toUriString(), true, "tall");
            element.setTitle(doctor.getName());
            element.setImageUrl(doctor.getPracticeImage());
            element.setSubtitle(doctor.getAddress1() + ",\n" + doctor.getCity() + " " + doctor.getPostcode());
            element.setButtons(Collections.singletonList(button));

            messageElementList.add(element);
        }
        return messageElementList;
    }
}
