package com.stackroute.exception;

@SuppressWarnings("serial")
public class RecommendAlreadyExistsException extends Exception {
	public RecommendAlreadyExistsException(String message) {
		super(message);
	}
}