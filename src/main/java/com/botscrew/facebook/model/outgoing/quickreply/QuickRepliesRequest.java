package com.botscrew.facebook.model.outgoing.quickreply;

import java.util.List;

import com.botscrew.facebook.model.UserId;

public class QuickRepliesRequest {
	private UserId recipient;
	private Message message;

	public UserId getRecipient() {
		return recipient;
	}

	public void setRecipient(UserId recipient) {
		this.recipient = recipient;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public static QuickRepliesRequest getRequest() {
		return new QuickRepliesRequest();
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {

		private UserId recipient;
		private List<QuickReply> quickReplies;
		private String text;

		private Builder() {
		}

		public Builder recipient(UserId recipient) {
			this.recipient = recipient;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder quickReplies(List<QuickReply> quickReplies) {
			this.quickReplies = quickReplies;
			return this;
		}

		public QuickRepliesRequest build() {
			QuickRepliesRequest quickRequest = new QuickRepliesRequest();
			Message message = new Message();
			message.setText(text);
			message.setQuickReplies(quickReplies);
			quickRequest.setMessage(message);
			quickRequest.setRecipient(recipient);
			return quickRequest;
		}

	}
}
