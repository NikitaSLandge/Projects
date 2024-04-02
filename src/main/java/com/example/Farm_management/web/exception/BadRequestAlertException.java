package com.example.Farm_management.web.exception;


public class BadRequestAlertException extends RuntimeException {
    private final String message;

    // Constructor to set the message
    public BadRequestAlertException(String message) {
        super(message);
        this.message = message;
    }

    // Override getMessage() method to return the message
   /* @Override
    public String getMessage() {
        return message;
    }*/
}

