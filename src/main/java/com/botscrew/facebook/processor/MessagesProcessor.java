package com.botscrew.facebook.processor;

import com.botscrew.facebook.model.incomming.MessageReceived;
import org.springframework.scheduling.annotation.Async;

public interface MessagesProcessor {
	@Async
	void processMessage(MessageReceived message);
}
