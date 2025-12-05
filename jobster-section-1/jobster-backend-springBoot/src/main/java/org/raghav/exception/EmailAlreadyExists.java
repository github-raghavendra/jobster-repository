package org.raghav.exception;

public class EmailAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 8608199952300482482L;

	public EmailAlreadyExists(String msg) {
		super(msg);
	}

}
