package com.revolut.kabanov.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Алексей
 * 
 */
public class ClientTest {

    @Test
    public void shouldDepositIncreaseBalanceWhenCalled() {
        Client client = new Client();
        client.setBalance(BigDecimal.valueOf(10));
        client.deposit(BigDecimal.valueOf(50));
        Assert.assertEquals(BigDecimal.valueOf(60), client.getBalance());
    }

    @Test
    public void shouldDepositDecreaseBalanceWhenCalled() {
        Client client = new Client();
        client.setBalance(BigDecimal.valueOf(70));
        client.withdraw(BigDecimal.valueOf(10));
        Assert.assertEquals(BigDecimal.valueOf(60), client.getBalance());
    }



}