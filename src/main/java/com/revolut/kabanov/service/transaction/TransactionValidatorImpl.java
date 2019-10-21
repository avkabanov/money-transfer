package com.revolut.kabanov.service.transaction;

import java.math.BigDecimal;

import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.InsufficietFundsException;
import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.FundsTransactionRequest;

/**
 * @author Алексей
 * 
 */
public class TransactionValidatorImpl implements TransactionValidator {
    @Override
    public void validateRequest(FundsTransactionRequest request) throws RequestValidationException {
        if (request.amount == null) {
            throw new RequestValidationException("Transfer amount can not be null");
        }

        if (request.fromId == request.toId) {
            throw new RequestValidationException(
                    "The sender and receiver of the transfer must be different clients");
        }

        if (request.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RequestValidationException(
                    "Transfer amount should be positive");
        }
    }

    @Override
    public void validateTransactionIsPossible(Client from, Client to, BigDecimal amount) throws InsufficietFundsException {

        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficietFundsException("Insufficient funds client " + from + " to transfer " + amount + ". " +
                    "Account balance is " + from.getBalance());
        }

    }
}
