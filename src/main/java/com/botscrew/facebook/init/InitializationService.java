package com.botscrew.facebook.init;

import com.botscrew.facebook.entity.enums.Postbacks;
import com.botscrew.facebook.messages.MessageKey;
import com.botscrew.facebook.messages.MessagesHolder;
import com.botscrew.facebook.messages.Payload;
import com.botscrew.facebook.model.outgoing.DomainWhiteList;
import com.botscrew.facebook.model.outgoing.buttons.GetStartedButton;
import com.botscrew.facebook.model.outgoing.buttons.StartedButtonRequest;
import com.botscrew.facebook.model.outgoing.menu.ContextMenuSettingRequest;
import com.botscrew.facebook.model.outgoing.menu.MenuAction;
import com.botscrew.facebook.model.outgoing.welcome.WelcomeMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitializationService {
    @Value("${refresh.welcome.message:true}")
    private Boolean refreshWelcomeMessage;
    @Value("${refresh.menu:true}")
    private Boolean refreshMenu;
    @Value("${facebook.welcome.message.url}")
    private String welcomeMessageUrl;
    @Value("${facebook.token}")
    private String token;
    @Value("${facebook.setwhitelisting.url}")
    private String WHITELISTING_URL;
    @Value("${facebook.set_started_button.url}")
    private String getStartedButtonURL;
    @Value("${system.base.url}")
    private String baseUrl;
    @Value("${system.base.privacy}")
    private String policyUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MessagesHolder messagesHolder;


    @Transactional
    private void tryToRefreshWelcomeMessage() {
        if (refreshWelcomeMessage) {
            restTemplate.postForObject(welcomeMessageUrl + token,
                    WelcomeMessageRequest.getFullRequest(Payload.GET_STARTED), String.class);
        }
    }

    private void setMenu() {
        if (refreshMenu) {
            List<MenuAction> actions = new ArrayList<>();
            //add menu items
            actions.add(MenuAction.createPayloadMenuAction(messagesHolder.getMessage(MessageKey.FROM_START),
                    Postbacks.INIT));
            ContextMenuSettingRequest request = ContextMenuSettingRequest.getFullRequest(actions);
            restTemplate.postForObject(welcomeMessageUrl + token, request, String.class);
        }
    }

    @PostConstruct
    public void init() {
        tryToRefreshWelcomeMessage();
        setMenu();
    }

    @PostConstruct
    public void setDomainWhiteList() {
        DomainWhiteList domainWhiteList = DomainWhiteList.createDomainWhiteListing();
        domainWhiteList.addDomain(baseUrl);
        domainWhiteList.addDomain(policyUrl);
        domainWhiteList.setAddActionType();
        restTemplate.postForObject(WHITELISTING_URL + token, domainWhiteList, String.class);

    }
    @PostConstruct
    public void setGetStartedButton(){
        StartedButtonRequest request = new StartedButtonRequest();
        request.setGetStarted(new GetStartedButton(Postbacks.INIT));
        restTemplate.postForObject(getStartedButtonURL + token, request,
                StartedButtonRequest.class);
    }


}
