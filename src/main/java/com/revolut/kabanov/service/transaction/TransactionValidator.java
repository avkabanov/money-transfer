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
public interface TransactionValidator {
    
    void validateRequest(FundsTransactionRequest request) throws RequestValidationException;

    void validateTransactionIsPossible(Client from, Client to, BigDecimal amount) throws InsufficietFundsException;
}
