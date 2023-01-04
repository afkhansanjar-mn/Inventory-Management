package com.modeln.exceptions;

public class NotValidException extends Exception{
	private static final long serialVersionUID = 1L;

	public NotValidException(String msg) {
		super(msg);
	}
}
