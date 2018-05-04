package com.botscrew.facebook.model.incomming;

public class Payload {
	private String url;
	private Coordinates coordinates;
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Payload [url=" + url + ", coordinates=" + coordinates + "]";
	}

}
