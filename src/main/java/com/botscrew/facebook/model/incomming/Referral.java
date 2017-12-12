package com.botscrew.facebook.model.incomming;

/**
 * Created by Vladislav on 11/16/2016.
 */
public class Referral {

    private String ref;
    private String source;
    private String type;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Referral{" +
                "ref='" + ref + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
