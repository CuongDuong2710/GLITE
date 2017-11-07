package com.glite.popeyes.exception;

/**
 * Created by tamhoang on 31/05/2016.
 *
 * Exception throw by the application when a User search can't return a valid result
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(final Throwable cause) {
        super(cause);
    }
}
