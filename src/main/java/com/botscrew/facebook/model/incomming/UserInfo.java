package com.botscrew.facebook.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/23/2016.
 */
public class UserInfo {

    @JsonProperty("id")
    private String pageId;

    @JsonProperty("recipient")
    private String userId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
