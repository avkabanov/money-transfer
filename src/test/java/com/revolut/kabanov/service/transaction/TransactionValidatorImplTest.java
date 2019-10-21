package com.revolut.kabanov.service.transaction;

import java.math.BigDecimal;

import org.junit.Test;

import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.FundsTransactionRequest;

/**
 * @author Kabanov Alexey
 */
public class TransactionValidatorImplTest {

    private TransactionValidator validator = new TransactionValidatorImpl();

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenTransferAmountIsNull() throws RequestValidationException {
        FundsTransactionRequest request = new FundsTransactionRequest();
        request.fromId = 1;
        request.toId = 2;
        request.amount = null;
        validator.validateRequest(request);
    }

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenTransferAmountZero() throws RequestValidationException {
        FundsTransactionRequest request = new FundsTransactionRequest();
        request.fromId = 1;
        request.toId = 2;
        request.amount = new BigDecimal(0);
        validator.validateRequest(request);
    }

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenTransferAmountNegative() throws RequestValidationException {
        FundsTransactionRequest request = new FundsTransactionRequest();
        request.fromId = 1;
        request.toId = 2;
        request.amount = new BigDecimal(-1);
        validator.validateRequest(request);
    }

    @Test(expected = RequestValidationException.class)
    public void shouldThrowExceptionWhenSenderAndReceiverIsTheSame() throws RequestValidationException {
        FundsTransactionRequest request = new FundsTransactionRequest();
        request.fromId = 1;
        request.toId = 1;
        request.amount = new BigDecimal(1);
        validator.validateRequest(request);
    }

    @Test
    public void shouldNotThrowExceptionWhenParametersAreCorrect() throws RequestValidationException {
        FundsTransactionRequest request = new FundsTransactionRequest();
        request.fromId = 1;
        request.toId = 2;
        request.amount = new BigDecimal(1);
        validator.validateRequest(request);
    }

}