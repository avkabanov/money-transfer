package com.revolut.kabanov.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.InsufficietFundsException;
import com.revolut.kabanov.service.client.ClientCache;
import com.revolut.kabanov.service.transaction.TransactionService;
import com.revolut.kabanov.service.transaction.TransactionServiceImpl;
import com.revolut.kabanov.service.transaction.TransactionValidator;

/**
 * @author Алексей
 */
@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    @Captor ArgumentCaptor<Client> clientCaptor;

    @Mock private ClientCache clientCache;
    @Mock private TransactionValidator transactionValidator;
    private TransactionService transactionService;

    @Before
    public void setup() {
        transactionService = new TransactionServiceImpl(transactionValidator, clientCache);
    }

    @Test
    public void shouldMoneyBeTransferedWhenBalanceIsFine() throws InsufficietFundsException {
        Client client1 = new Client(1L, "client1", BigDecimal.valueOf(100));
        Client client2 = new Client(2L, "client2", BigDecimal.valueOf(50));

        transactionService.transferFunds(client1, client2, BigDecimal.valueOf(20));

        Mockito.verify(clientCache, Mockito.times(2)).createOrUpdateClient(clientCaptor.capture());

        List<Client> values = clientCaptor.getAllValues();
        Assert.assertEquals(1, values.get(0).getId().longValue());
        Assert.assertEquals(BigDecimal.valueOf(80), values.get(0).getBalance());

        Assert.assertEquals(2, values.get(1).getId().longValue());
        Assert.assertEquals(BigDecimal.valueOf(70), values.get(1).getBalance());
    }

    @Test
    public void shouldValidatorBeInvokedWhenTransferring() throws InsufficietFundsException {
        Client client1 = new Client(1L, "client1", BigDecimal.valueOf(10));
        Client client2 = new Client(2L, "client2", BigDecimal.valueOf(50));

        transactionService.transferFunds(client1, client2, BigDecimal.valueOf(20));

        Mockito.verify(transactionValidator, Mockito.times(1))
                .validateTransactionIsPossible(Mockito.any(Client.class), Mockito.any(Client.class),
                        Mockito.any(BigDecimal.class));

    }

    @Test
    public void shouldPersistClientWhenWithdrawCalled() {
        Client client1 = new Client(1L, "client1", BigDecimal.valueOf(100));

        transactionService.withdraw(client1, BigDecimal.valueOf(30));

        Mockito.verify(clientCache, Mockito.times(1)).createOrUpdateClient(clientCaptor.capture());

        Assert.assertEquals(1L, clientCaptor.getValue().getId().longValue());
        Assert.assertEquals(BigDecimal.valueOf(70), clientCaptor.getValue().getBalance());
    }

    @Test
    public void shouldPersistClientWhenDepositCalled() {
        Client client1 = new Client(1L, "client1", BigDecimal.valueOf(100));

        transactionService.deposit(client1, BigDecimal.valueOf(30));

        Mockito.verify(clientCache, Mockito.times(1)).createOrUpdateClient(clientCaptor.capture());

        Assert.assertEquals(1L, clientCaptor.getValue().getId().longValue());
        Assert.assertEquals(BigDecimal.valueOf(130), clientCaptor.getValue().getBalance());
    }
}