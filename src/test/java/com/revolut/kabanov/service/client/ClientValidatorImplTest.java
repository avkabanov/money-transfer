package com.revolut.kabanov.service.client;

import java.math.BigDecimal;

import org.junit.Test;

import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.CreateClientRequest;

/**
 * @author Kabanov Alexey
 */
public class ClientValidatorImplTest {
    
    private ClientValidator clientValidator = new ClientValidatorImpl();

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenClientNameIsNull() throws RequestValidationException {
        CreateClientRequest request = new CreateClientRequest();
        request.setName(null);
        request.setBalance(BigDecimal.valueOf(100));
        clientValidator.validateCreateClientRequest(request);
    }

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenClientNameIsEmpty() throws RequestValidationException {
        CreateClientRequest request = new CreateClientRequest();
        request.setName("");
        request.setBalance(BigDecimal.valueOf(100));
        clientValidator.validateCreateClientRequest(request);
    }

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenBalanceIsNegative() throws RequestValidationException {
        CreateClientRequest request = new CreateClientRequest();
        request.setName("client1");
        request.setBalance(BigDecimal.valueOf(-1));
        clientValidator.validateCreateClientRequest(request);
    }

    @Test
    public void shouldNotThrowExceptionWhenBalanceZero() throws RequestValidationException {
        CreateClientRequest request = new CreateClientRequest();
        request.setName("client1");
        request.setBalance(BigDecimal.valueOf(0));
        clientValidator.validateCreateClientRequest(request);
    }

    @Test
    public void shouldNotThrowExceptionWhenRequestIsFine() throws RequestValidationException {
        CreateClientRequest request = new CreateClientRequest();
        request.setName("client1");
        request.setBalance(BigDecimal.valueOf(100));
        clientValidator.validateCreateClientRequest(request);
    }
}