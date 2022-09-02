package com.elisarovani.helpdesk.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public static final long serialVersionUID = 1L;

    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
