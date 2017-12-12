package com.botscrew.facebook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Doctor {
    @JsonProperty("_id")
    private String id;
    private String name;
    private String practice;
    private String specialism;
    @JsonProperty("botid")
    private String botId;
    @JsonProperty("lat")
    private Double latitude;
    @JsonProperty("long")
    private Double longitude;
    private String description;
    private String practiceImage;
    private String practionerImage;
    private String url;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String country;
    private String postcode;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String saturday;
    private String sunday;
    private Integer distance;

    public Doctor(String id, String name, String practice, String specialism, String botId, Double latitude,
                  Double longitude, String description, String practiceImage, String practionerImage, String url,
                  String phone, String address1, String address2, String city, String country, String postcode,
                  String monday, String tuesday, String wednesday, String thursday, String friday, String saturday,
                  String sunday, Integer distance) {

        this.id = id;
        this.name = name;
        this.practice = practice;
        this.specialism = specialism;
        this.botId = botId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.practiceImage = practiceImage;
        this.practionerImage = practionerImage;
        this.url = url;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
        this.distance = distance;
    }
    public Doctor() {
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPractice() {
        return practice;
    }

    public void setPractice(String practice) {
        this.practice = practice;
    }

    public String getSpecialism() {
        return specialism;
    }

    public void setSpecialism(String specialism) {
        this.specialism = specialism;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPracticeImage() {
        return practiceImage;
    }

    public void setPracticeImage(String practiceImage) {
        this.practiceImage = practiceImage;
    }

    public String getPractionerImage() {
        return practionerImage;
    }

    public void setPractionerImage(String practionerImage) {
        this.practionerImage = practionerImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }
}
