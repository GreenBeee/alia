package com.botscrew.facebook.model.incomming;

public class Postback {
	private String payload;
	private Referral referral;

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Referral getReferral() {
		return referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	@Override
	public String toString() {
		return "Postbacks [payload=" + payload + "]";
	}
	
}
