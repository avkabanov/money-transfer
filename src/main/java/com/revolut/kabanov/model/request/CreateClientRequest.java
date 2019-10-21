package com.revolut.kabanov.model.request;

import java.math.BigDecimal;

/**
 * @author Алексей
 * 
 */
public class CreateClientRequest {

    private String name;
    private BigDecimal balance = new BigDecimal(0);

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
