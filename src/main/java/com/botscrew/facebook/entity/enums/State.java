package com.botscrew.facebook.entity.enums;

public interface State {
    String INITIAL = "INITIAL";
    String WAIT_TERMS = "WAIT_TERMS";
    String ACCEPTED_TERMS = "ACCEPTED_TERMS";

    String WAIT_PERMISSION_LOCATION = "WAIT_PERMISSION_LOCATION";
    String ACCEPTED_LOCATION = "ACCEPTED_LOCATION";

    String WAIT_LOCATION = "WAIT_LOCATION";
    String HAD_LOCATION = "HAD_LOCATION";
}
