package com.revolut.kabanov.service.client;

import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.CreateClientRequest;

/**
 * @author Алексей
 * 
 */
public interface ClientValidator {

    void validateCreateClientRequest(CreateClientRequest request) throws RequestValidationException;

}
