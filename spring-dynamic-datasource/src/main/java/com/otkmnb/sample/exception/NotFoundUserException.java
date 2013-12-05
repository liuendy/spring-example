package com.otkmnb.sample.exception;

public class NotFoundUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundUserException() {
        super();
    }

    public NotFoundUserException(String message) {
        super(message);
    }

}
