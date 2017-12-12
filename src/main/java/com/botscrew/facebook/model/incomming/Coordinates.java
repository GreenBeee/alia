package com.botscrew.facebook.model.incomming;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {
	@JsonProperty("long")
	private Double longitude;
	@JsonProperty("lat")
	private Double latitude;
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "Coordinates [longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
}
