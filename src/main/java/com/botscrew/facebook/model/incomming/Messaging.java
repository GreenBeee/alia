package com.botscrew.facebook.model.incomming;

import com.botscrew.facebook.model.UserId;
import com.botscrew.facebook.model.payment.Payment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Messaging {
	private UserId sender;
	private UserId recipient;
	private long timestamp;
	private Payment payment;
	private Referral referral;
	@JsonProperty("account_linking")
	private Account account;
	private Message message;
	private Postback postback;

	public UserId getSender() {
		return sender;
	}

	public void setSender(UserId sender) {
		this.sender = sender;
	}

	public UserId getRecipient() {
		return recipient;
	}

	public void setRecipient(UserId recipient) {
		this.recipient = recipient;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Referral getReferral() {
		return referral;
	}

	public void setReferral(Referral referral) {
		this.referral = referral;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Postback getPostback() {
		return postback;
	}

	public void setPostback(Postback postback) {
		this.postback = postback;
	}

	@Override
	public String toString() {
		return "Messaging{" +
				"sender=" + sender +
				", recipient=" + recipient +
				", timestamp=" + timestamp +
				", payment=" + payment +
				", referral=" + referral +
				", account=" + account +
				", message=" + message +
				", postback=" + postback +
				'}';
	}
}
