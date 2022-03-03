package com.stackroute.exception;

@SuppressWarnings("serial")
public class FavouriteAlreadyExistsException extends Exception {
	public FavouriteAlreadyExistsException(String message) {
		super(message);
	}
}