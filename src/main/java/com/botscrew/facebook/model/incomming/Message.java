package com.botscrew.facebook.model.incomming;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
	private String mid;
	private Integer seq;
	private List<Attachment> attachments;
	private String text;
	@JsonProperty("quick_reply")
	private Postback quickReply;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	public Postback getQuickReply() {
		return quickReply;
	}

	public void setQuickReply(Postback quickReply) {
		this.quickReply = quickReply;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", seq=" + seq + ", attachments=" + attachments + ", text=" + text
				+ ", quickReply=" + quickReply + "]";
	}

}
