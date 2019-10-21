package com.revolut.kabanov.service.client;

import java.math.BigDecimal;

import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.CreateClientRequest;

/**
 * @author Алексей
 * 
 */
public class ClientValidatorImpl implements ClientValidator {
    
    @Override
    public void validateCreateClientRequest(CreateClientRequest request) throws RequestValidationException {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new RequestValidationException("Name can not be empty");
        }

        if (request.getBalance().compareTo(BigDecimal.ZERO)  < 0 ) {
            throw new RequestValidationException("Balance can not be negative");
        }
    }
}
