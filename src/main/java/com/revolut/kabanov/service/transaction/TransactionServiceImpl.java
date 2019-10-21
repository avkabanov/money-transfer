package com.revolut.kabanov.service.transaction;

import java.math.BigDecimal;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import com.google.inject.Inject;
import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.InsufficietFundsException;
import com.revolut.kabanov.service.client.ClientCache;

/**
 * @author Алексей
 * 
 */
@ThreadSafe
public class TransactionServiceImpl implements TransactionService {

    private TransactionValidator transactionValidator;  
    private ClientCache clientCache;

    @Inject
    public TransactionServiceImpl(TransactionValidator transactionValidator,
                                  ClientCache clientCache) {
        this.transactionValidator = transactionValidator;
        this.clientCache = clientCache;
    }

    @Override
    public BigDecimal transferFunds(@Nonnull Client from, @Nonnull Client to,
                                    @Nonnull BigDecimal amount) throws InsufficietFundsException {
        Client firstLock = from.getId() < to.getId() ? from : to;
        Client secondLock = firstLock == from ? to : from;

        try {
            firstLock.clientOperationLock();
            secondLock.clientOperationLock();

            transactionValidator.validateTransactionIsPossible(from, to, amount);

            withdraw(from, amount);
            deposit(to, amount);
            return amount;
        } finally {
            secondLock.accountOperationUnlock();
            firstLock.accountOperationUnlock();
        }
    }

    @Override
    public void withdraw(@Nonnull Client client, @Nonnull BigDecimal amount) {
        try {
            client.clientOperationLock();
            client.withdraw(amount);
            clientCache.createOrUpdateClient(client);
        } finally {
            client.accountOperationUnlock();
        }
    }

    @Override
    public void deposit(@Nonnull Client client, @Nonnull BigDecimal amount) {
        try {
            client.clientOperationLock();
            client.deposit(amount);
            clientCache.createOrUpdateClient(client);
        } finally {
            client.accountOperationUnlock();
        }
    }
}
