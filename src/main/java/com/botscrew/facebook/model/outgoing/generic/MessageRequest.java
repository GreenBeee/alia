package com.botscrew.facebook.model.outgoing.generic;

import java.util.List;

import com.botscrew.facebook.model.UserId;

public class MessageRequest {
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

	@Override
	public String toString() {
		return "MessageRequest [recipient=" + recipient + ", message=" + message + "]";
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {

		private UserId recipient;
		private List<MessageElement> elements;

		private Builder() {
		}

		public Builder recipient(UserId userId) {
			this.recipient = userId;
			return this;
		}

		public Builder elements(List<MessageElement> elements) {
			this.elements = elements;
			return this;
		}

		public MessageRequest build() {
			MessageRequest request = new MessageRequest();
			Message message = new Message();
			request.setMessage(message);
			Attachment attachment = new Attachment();
			message.setAttachment(attachment);
			attachment.setType("template");
			Payload payload = new Payload();
			attachment.setPayload(payload);
			payload.setTemplateType("generic");
			request.setRecipient(recipient);
			payload.setElements(elements);
			return request;
		}

		public MessageRequest buildListTemplate() {
			MessageRequest request = build();
			request.getMessage().getAttachment().getPayload().setTemplateType("generic");
			return request;
		}
	}
}
