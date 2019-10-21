package com.revolut.kabanov.model.exception;

/**
 * @author Алексей
 * 
 */
public class RequestValidationException extends Exception {
    public RequestValidationException(String message) {
        super(message);
    }
}
