package com.revolut.kabanov.model.exception;

/**
 * @author Алексей
 * 
 */
public class InsufficietFundsException extends TransactionValidationException {

    public InsufficietFundsException(String message) {
        super(message);
    }
}
