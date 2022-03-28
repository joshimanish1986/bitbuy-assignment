package com.assignment.bitbuy.exception;

public class UnauthorizedUserException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedUserException(String msg) {
        super(msg);
    }
}
