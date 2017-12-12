package com.botscrew.facebook.model.outgoing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 11/15/2016.
 */
public class DomainWhiteList {
    @JsonProperty("setting_type")
    private String settingType;
    @JsonProperty("whitelisted_domains")
    private List<String> domains = new ArrayList<>();
    @JsonProperty("domain_action_type")
    private String actionType;

    public static DomainWhiteList createDomainWhiteListing() {
        DomainWhiteList whiteList = new DomainWhiteList();
        whiteList.setSettingType("domain_whitelisting");
        return whiteList;
    }

    public void setAddActionType() {
        this.setActionType("add");
    }

    public void addDomain(String domain) {
        domains.add(domain);
    }

    public String getSettingType() {
        return settingType;
    }

    public void setSettingType(String settingType) {
        this.settingType = settingType;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
}
