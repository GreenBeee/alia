package com.botscrew.facebook.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vladislav on 11/17/2016.
 */
public class Account {
    private String status;
    @JsonProperty("authorization_code")
    private String authorizationCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public String toString() {
        return "Account{" +
                "status='" + status + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                '}';
    }
}
