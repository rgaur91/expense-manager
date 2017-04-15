package com.pr.expense.exception;

import com.pr.commons.dto.Message;
import com.pr.commons.exception.ExceptionWithProperties;

/**
 */
public class AuthenticationException extends org.springframework.security.core.AuthenticationException
		implements ExceptionWithProperties {

	private Message properties;

	public AuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}

	public AuthenticationException(String msgKey) {
		super(msgKey);
		properties = new Message(msgKey);
	}

	public AuthenticationException(String msgKey, String msg) {
		super(msg);
		properties = new Message(msgKey);
		properties.put("message", msg);
	}

	public AuthenticationException(Message message) {
		super(message.getMessageKey());
		properties = message;
	}

	@Override
	public Message getProperties() {
		return properties;
	}
}
