package com.botscrew.facebook.model.outgoing.generic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/14/2016.
 */
public class ListTemplateMessageElement extends MessageElement {
    @JsonProperty("default_action")
    private DefaultAction defaultAction;

    public ListTemplateMessageElement() {
    }



    public ListTemplateMessageElement(String title, String subtitle, String imageUrl) {
        this.setTitle(title);
        this.setSubtitle(subtitle);
        this.setImageUrl(imageUrl);
    }

    public DefaultAction getDefaultAction() {
        return defaultAction;
    }

    public void setDefaultAction(DefaultAction defaultAction) {
        this.defaultAction = defaultAction;
    }
}
