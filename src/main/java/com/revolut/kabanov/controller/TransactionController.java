package com.revolut.kabanov.controller;

import java.math.BigDecimal;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.ClientNotFoundException;
import com.revolut.kabanov.model.exception.InsufficietFundsException;
import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.FundsTransactionRequest;
import com.revolut.kabanov.model.response.FundsTransactionResponse;
import com.revolut.kabanov.service.client.ClientCache;
import com.revolut.kabanov.service.transaction.TransactionService;
import com.revolut.kabanov.service.transaction.TransactionValidator;

/**
 * @author Алексей
 */
@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionValidator transactionValidator;
    private final ClientCache clientCache;

    @Inject
    public TransactionController(TransactionService transactionService,
                                 TransactionValidator transactionValidator,
                                 ClientCache clientCache) {
        this.transactionService = transactionService;
        this.transactionValidator = transactionValidator;
        this.clientCache = clientCache;
    }

    @POST
    public Response createTransaction(FundsTransactionRequest request) throws InsufficietFundsException, 
            ClientNotFoundException, RequestValidationException {
        transactionValidator.validateRequest(request);

        Client from = clientCache.getClient(request.fromId);
        Client to = clientCache.getClient(request.toId);

        BigDecimal resultAmount = transactionService.transferFunds(from, to, request.amount);
        return Response.ok(
                new FundsTransactionResponse(from.getId(), to.getId(), resultAmount)
        ).build();
    }
}
