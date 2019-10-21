package com.revolut.kabanov.model.response;

import java.math.BigDecimal;

import com.revolut.kabanov.model.Client;

/**
 * @author Алексей
 * 
 */
public class GetClientResponse {

    private String name;
    private BigDecimal balance;

    public GetClientResponse(Client client) {
        this.name = client.getName();
        this.balance = client.getBalance();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
