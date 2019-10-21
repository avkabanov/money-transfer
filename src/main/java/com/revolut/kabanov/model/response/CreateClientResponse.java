package com.revolut.kabanov.model.response;

import java.math.BigDecimal;

import com.revolut.kabanov.model.Client;

/**
 * @author Алексей
 * 
 */
public class CreateClientResponse {
    
    private long id;
    private String name;
    private BigDecimal balance;

    public CreateClientResponse() {
    }

    public CreateClientResponse(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.balance = client.getBalance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
