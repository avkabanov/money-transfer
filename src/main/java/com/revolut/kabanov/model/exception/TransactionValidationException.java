package com.revolut.kabanov.model.exception;

/**
 * @author Kabanov Alexey
 */
public class TransactionValidationException extends Exception {
    public TransactionValidationException(String message) {
        super(message);
    }
}
